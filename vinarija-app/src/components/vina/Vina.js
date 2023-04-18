import React, { useEffect, useState } from "react";
import { Table, Button, Form, ButtonGroup} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";

const Vina = () => {
  
  const [vina, setVina] = useState([])
  const [vinarije, setVinarije] = useState([])
  const [narucenaKolicina, setNarucenaKolicina] = useState()
  const [kupljenaKolicina, setKupljenaKolicina] = useState()
  const [search, setSearch] = useState({ vinarijaId: -1, vinoIme: ""})
  const [pageNo, setPageNo] = useState(0)
  const [totalPages, setTotalPages] = useState(1)
  const navigate = useNavigate()

  useEffect(()=>{
    getData();
  }, [])

  const getData = () => {
    getVinarije();
    getVina(0);
  }

  const getVina = (page) => {
    let config = { params: {
      pageNo: page
    } };

    //Sledeca 2 if-a su tu zbog search-a
    if (search.vinoIme != "") {
      config.params.vinoIme = search.vinoIme;
    }

    if (search.vinarijaId != -1) {
      config.params.vinarijaId = search.vinarijaId;
    }

    TestAxios.get("/vina", config)
    .then((result)=>{
      setPageNo(page)
      setVina(result.data)
      setTotalPages(result.headers["total-pages"])
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const getVinarije = () => {
    TestAxios.get("/vinarije").then((result) => {
      setVinarije(result.data)
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const kupi = (vinoId) => {
    console.log(vinoId);
    TestAxios.put("/vina/kupi/" + vinoId + "/" + kupljenaKolicina)
    .then((result) => {
      console.log(result.data);
      getVina(pageNo);
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const naruci = (vinoId) => {
    TestAxios.put("/vina/naruci/" + vinoId + "/" + narucenaKolicina)
    .then((result) => {
      console.log(result.data);
      getVina(pageNo);
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const goToAdd = () => {
    navigate("/vina/add");
  }

  const doDelete = (vinoId) => {
      TestAxios.delete("/vina/" + vinoId)
      .then(()=>{
        var nextPage
        if(pageNo==totalPages-1 && vina.length==1){
          nextPage = pageNo-1
        }else{
          nextPage = pageNo
        }
        getVina(nextPage);
      }).catch((error) => {
        alert("Nije uspelo brisanje.");
      })
  }

  const naruciInputChange = (event) => {
    const value = event.target.value;
    setNarucenaKolicina(value);
  }

  const kupiInputChange = (event) => {
    const value = event.target.value;
    setKupljenaKolicina(value);
  }

  const searchValueInputChange = (event) => {
    let newSearch = {...search}

    const name = event.target.name;
    const value = event.target.value;

    newSearch[name] = value
    setSearch(newSearch);
  }

  const doSearch = () => {
    getVina(0);
  }

  const renderHeader = () => {
    const admin = window.localStorage['role']=="ROLE_ADMIN";
    return  <tr>
    <th>Naziv vina</th>
    <th>Vinarija</th>
    <th>Tip</th>
    <th hidden={admin}>Opis</th>
    <th>Godina proizvodnje</th>
    <th>Cena po flasi (rsd)</th>
    <th hidden={!admin}>Broj preostalih flasa</th>
    <th hidden={admin}></th>
    <th hidden={admin}></th>
    <th hidden={!admin}></th>
    <th hidden={!admin}></th>
    <th hidden={!admin}></th>
  </tr>;
  }

  const renderBody = () => {
    const admin = window.localStorage['role']=="ROLE_ADMIN";
    return vina.map((vino) => {
    return <tr key={vino.id}>
        <td>{vino.ime}</td>
        <td>{vino.vinarijaIme}</td>
        <td>{vino.tipVinaIme}</td>
        <td hidden={admin}>{vino.opis}</td>
        <td>{vino.godinaProizvodnje}</td>
        <td>{vino.cenaFlase}</td>
        <td hidden={!admin}>{vino.brojDostupnihFlasa}</td>
        <td hidden={admin}>
        <Form.Control
          name="brojKupljenihFlasa"
          as="input"
          onChange={(e) => kupiInputChange(e)}>
        </Form.Control>
        </td>
        <td hidden={admin}> 
        <Button
          variant="info"
          onClick={() => kupi(vino.id)}
          style={{ marginLeft: 5 }}>
          Kupi
        </Button>
        </td>
        <td hidden={!admin}> 
          <Button
            variant="danger"
            onClick={() => doDelete(vino.id)}
            style={{ marginLeft: 5 }}>
            Obrisi
          </Button>
        </td>
        <td hidden={!admin || (admin && vino.brojDostupnihFlasa > 10)}>
        <Form.Control
        name="brojNarucenihFlasa"
        as="input"
        onChange={(e) => naruciInputChange(e)}>
        </Form.Control>
        </td>
        <td hidden={!admin || (admin && vino.brojDostupnihFlasa > 10)}>
        <Button
          variant="info"
          onClick={() => naruci(vino.id)}
          style={{ marginLeft: 5 }}>
          Naruci
        </Button>
      </td>
      </tr>
  })}


  return (
    <div>
      <h1>Vina</h1>
      {/*Deo za Search*/}
      <Form style={{marginTop:10}}>
      <Form.Group>
          <Form.Label>Vinarija</Form.Label>
          <Form.Control
            onChange={(event) => searchValueInputChange(event)}
            name="vinarijaId"
            value={search.vinarijaId}
            as="select">
            <option value={-1}>Izaberi vinariju</option>
            {vinarije.map((vinarija) => {
              return (
                <option value={vinarija.id} key={vinarija.id}>
                  {vinarija.ime}
                </option>
              );
            })}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Naziv vina</Form.Label>
          <Form.Control
            value={search.vinoIme}
            placeholder="Naziv vina"
            name="vinoIme"
            as="input"
            onChange={(e) => searchValueInputChange(e)}
          ></Form.Control>
        </Form.Group>
        <Button onClick={() => doSearch()}>Pretraga</Button>
      </Form>
      
      {/*Deo za ADD dugme*/}
      {window.localStorage['role']=="ROLE_ADMIN"?
      <ButtonGroup style={{ marginTop: 25, float:"left"}}>
        <Button variant="info" onClick={() => goToAdd()}>
          Kreiraj vino
        </Button>
      </ButtonGroup>
      :null} 
        {/*Deo za prikaz Vina*/}
      <ButtonGroup style={{ marginTop: 25, float:"right"}}>
        <Button 
          style={{ margin: 3, width: 90}}
          disabled={pageNo==0} onClick={()=>getVina(pageNo-1)}>
          Prethodna
        </Button>
        <Button
          style={{ margin: 3, width: 90}}
          disabled={pageNo==totalPages-1} onClick={()=>getVina(pageNo+1)}>
          Sledeca
        </Button>
      </ButtonGroup>
      {/* Tabela za prikaz vina */}
      <Table bordered striped style={{ marginTop: 5 }}>
        <thead className="thead-dark">
          {renderHeader()}
        </thead>
        <tbody>
          {renderBody()} 
        </tbody>
      </Table>
    </div>
  );
}

export default Vina
