package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.TipVina;
import com.ftninformatika.jwd.modul3.test.service.TipVinaService;
import com.ftninformatika.jwd.modul3.test.support.TipVinaToTipVinaDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.TipVinaDTO;

@RestController
@RequestMapping(value = "/api/tipovi", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TipVinaController {

	 @Autowired
	 private TipVinaService tipVinaService;

	 @Autowired
	 private TipVinaToTipVinaDTO toTipVinaDTO;

	 @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @GetMapping
	 public ResponseEntity<List<TipVinaDTO>> getAll(){

		List<TipVina> tipoviVina = tipVinaService.findAll();

		return new ResponseEntity<>(toTipVinaDTO.convert(tipoviVina), HttpStatus.OK);
	}





}
