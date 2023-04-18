import React, { useEffect, useState } from "react";
import { Button, Form} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";

const VinaAdd = () => {
  //potrebno je zbog create-a
  const emptyVino = {
    ime: "",
    opis: "",
    godinaProizvodnje: "",
    cenaFlase: "",
    tipVinaId: -1,
    vinarijaId: -1
  }
  const [vino, setVino] = useState(emptyVino)
  const [vinarije, setVinarije] = useState([])
  const [tipoviVina, setTipoviVina] = useState([])
  const navigate = useNavigate()

  useEffect(()=>{
    getData();
  }, [])

  const getData = () => {
    getVinarije();
    getTipoviVina();
  }

  const getVinarije = () => {
    TestAxios.get("/vinarije").then((result) => {
      setVinarije(result.data)
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const getTipoviVina = () => {
    TestAxios.get("/tipovi").then((result) => {
      setTipoviVina(result.data)
    }).catch(()=>{
      alert("Nije uspelo dobavljanje.");
    })
  }

  const doAdd = () => {
    TestAxios.post("/vina/", vino)
    .then(()=>{
      //bitno je da bi "resetovali" polja za kreiranje nakon kreiranja
      let vino = {
        ime: "",
        opis: "",
        godinaProizvodnje: "",
        cenaFlase: "",
        tipVinaId: -1,
        vinarijaId: -1
      };
      setVino(vino)
      navigate("/vina");
    }).catch(() =>{
      alert("Nije uspelo dodavanje.");
    })
  }

  const addValueInputChange = (event) => {
    let novoVino = {...vino}

    const name = event.target.name;
    const value = event.target.value;

    novoVino[name] = value
    setVino(novoVino);
  }

  const canCreateVino = () => {
    return vino.ime!="" && 
      vino.opis!="" && vino.godinaProizvodnje>0 && vino.cenaFlase>0
       && vino.tipVinaId != -1 && vino.vinarijaId != -1
  }

  return (
    <div>
      {/*Deo za ADD*/}
      <Form>
        <Form.Group>
          <Form.Label>Naziv vina</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Naziv vina"
            name="ime"
            value={vino.ime}
            as="input"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Opis vina</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Opis vina"
            name="opis"
            value={vino.opis}
            as="textarea"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Godina proizvodnje</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Godina proizvodnje"
            name="godinaProizvodnje"
            value={vino.godinaProizvodnje}
            as="input"
            type="number"
            min = "0"
            step = "1"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Cena po flasi</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            placeholder="Cena po flasi"
            name="cenaFlase"
            value={vino.cenaFlase}
            as="input"
            type="number"
            min = "0"
            step = "1"
          ></Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Tip vina</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            name="tipVinaId"
            value={vino.tipVinaId}
            as="select">
            <option value={-1}>Izaberi tip vina</option>
            {tipoviVina.map((tipVina) => {
              return (
                <option value={tipVina.id} key={tipVina.id}>
                  {tipVina.ime}
                </option>
              );
            })}
          </Form.Control>
        </Form.Group>
        <Form.Group>
          <Form.Label>Vinarija</Form.Label>
          <Form.Control
            onChange={(event) => addValueInputChange(event)}
            name="vinarijaId"
            value={vino.vinarijaId}
            as="select"
          >
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
        <Button disabled = {!canCreateVino()} variant="primary" onClick={() => doAdd()}>
          Kreiraj vino
        </Button>
      </Form>
    </div>
  );
}

export default VinaAdd
