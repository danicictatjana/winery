package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Vino;
import com.ftninformatika.jwd.modul3.test.service.VinoService;
import com.ftninformatika.jwd.modul3.test.support.VinoDTOToVino;
import com.ftninformatika.jwd.modul3.test.support.VinoToVinoDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.VinoDTO;

@RestController
@RequestMapping(value = "/api/vina", produces = MediaType.APPLICATION_JSON_VALUE)
public class VinoController {

	 	@Autowired
	    private VinoService vinoService;

	    @Autowired
	    private VinoDTOToVino toVino;

	    @Autowired
	    private VinoToVinoDTO toVinoDTO;

	    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	    @GetMapping
	    public ResponseEntity<List<VinoDTO>> getAll(
	    		@RequestParam(required=false) Long vinarijaId,
	    		@RequestParam(required=false) String vinoIme,
	            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

	        Page<Vino> page;
	        if(vinoIme != null || vinarijaId != null) {
	        	page = vinoService.find(vinarijaId, vinoIme, pageNo);
	        }else {
	        	page = vinoService.findAll(pageNo);
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	        return new ResponseEntity<>(toVinoDTO.convert(page.getContent()), headers, HttpStatus.OK);
	    }

	    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	    @GetMapping("/{id}")
	    public ResponseEntity<VinoDTO> getOne(@PathVariable Long id){
	        Vino vino = vinoService.findOne(id);

	        if(vino != null) {
	            return new ResponseEntity<>(toVinoDTO.convert(vino), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<VinoDTO> create(@Valid @RequestBody VinoDTO vinoDTO){
	    	Vino vino = toVino.convert(vinoDTO);

	        if(vino.getTipVina() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        vino.setBrojDostupnihFlasa(0);
	        Vino sacuvanoVino = vinoService.save(vino);

	        return new ResponseEntity<>(toVinoDTO.convert(sacuvanoVino), HttpStatus.CREATED);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<VinoDTO> update(@PathVariable Long id, @Valid @RequestBody VinoDTO vinoDTO){

	        if(!id.equals(vinoDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Vino vino = toVino.convert(vinoDTO);

	        if(vino.getTipVina() == null && vino.getVinarija() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Vino sacuvanoVino = vinoService.update(vino);

	        return new ResponseEntity<>(toVinoDTO.convert(sacuvanoVino),HttpStatus.OK);
	    }

	    @PreAuthorize("hasRole('KORISNIK')")
	    @PutMapping(value= "/kupi/{vinoId}/{kupljenaKolicina}")
	    public ResponseEntity<VinoDTO> kupi(@PathVariable Long vinoId, @PathVariable Integer kupljenaKolicina){
	    	Vino vino = vinoService.findOne(vinoId);
	    	if(vino.getBrojDostupnihFlasa() < kupljenaKolicina) {
	    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    	}
	    	vino.setBrojDostupnihFlasa(vino.getBrojDostupnihFlasa() - kupljenaKolicina);
	    	Vino sacuvanoVino = vinoService.save(vino);
	    	return new ResponseEntity<>(toVinoDTO.convert(sacuvanoVino),HttpStatus.OK);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping(value= "/naruci/{vinoId}/{narucenaKolicina}")
	    public ResponseEntity<VinoDTO> naruci(@PathVariable Long vinoId, @PathVariable Integer narucenaKolicina){
	    	Vino vino = vinoService.findOne(vinoId);
	    	vino.setBrojDostupnihFlasa(vino.getBrojDostupnihFlasa() + narucenaKolicina);
	    	Vino sacuvanoVino = vinoService.save(vino);
	    	return new ResponseEntity<>(toVinoDTO.convert(sacuvanoVino),HttpStatus.OK);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){

	    	Vino obrisanoVino = vinoService.delete(id);

	        if(obrisanoVino != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
