package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Vinarija;

public interface VinarijaService {

	List<Vinarija> findAll();

	Vinarija findOne(Long id);

}
