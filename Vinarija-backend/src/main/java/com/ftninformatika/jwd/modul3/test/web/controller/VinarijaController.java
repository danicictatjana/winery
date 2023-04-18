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

import com.ftninformatika.jwd.modul3.test.model.Vinarija;
import com.ftninformatika.jwd.modul3.test.service.VinarijaService;
import com.ftninformatika.jwd.modul3.test.support.VinarijaToVinarijaDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.VinarijaDTO;

@RestController
@RequestMapping(value = "/api/vinarije", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class VinarijaController {

	@Autowired
	 private VinarijaService vinarijaService;

	 @Autowired
	 private VinarijaToVinarijaDTO toVinarijaDTO;

	 @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @GetMapping
	 public ResponseEntity<List<VinarijaDTO>> getAll(){

		List<Vinarija> vinarije = vinarijaService.findAll();

		return new ResponseEntity<>(toVinarijaDTO.convert(vinarije), HttpStatus.OK);
	}
}
