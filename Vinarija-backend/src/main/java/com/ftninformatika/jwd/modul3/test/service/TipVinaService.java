package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.TipVina;

public interface TipVinaService {

	List<TipVina> findAll();

	TipVina findOne(Long id);
}
