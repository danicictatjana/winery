package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.model.Vino;

public interface VinoService {

	Vino findOne(Long id);

    Page<Vino> findAll(Integer pageNo);

    Vino save(Vino vino);

    Vino update(Vino vino);

    Vino delete(Long id);

    Page<Vino> find(Long vinarijaId, String ime, Integer pageNo);

    List<Vino> findByVinarijaId(Long vinarijaId);

}
