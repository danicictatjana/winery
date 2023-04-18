package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.TipVina;
import com.ftninformatika.jwd.modul3.test.repository.TipVinaRepository;
import com.ftninformatika.jwd.modul3.test.service.TipVinaService;

@Service
public class JpaTipVinaService implements TipVinaService{

	@Autowired
    private TipVinaRepository tipVinaRepository;

	@Override
	public List<TipVina> findAll() {
		return tipVinaRepository.findAll();
	}

	@Override
	public TipVina findOne(Long id) {
		return tipVinaRepository.findOneById(id);
	}

}
