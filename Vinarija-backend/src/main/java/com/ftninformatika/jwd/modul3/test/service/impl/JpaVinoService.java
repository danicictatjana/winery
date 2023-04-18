package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Vino;
import com.ftninformatika.jwd.modul3.test.repository.VinoRepository;
import com.ftninformatika.jwd.modul3.test.service.VinoService;

@Service
@Transactional
public class JpaVinoService implements VinoService {

	@Autowired
    private VinoRepository vinoRepository;

	@Override
	public Vino findOne(Long id) {
		return vinoRepository.findOneById(id);
	}

	@Override
	public Page<Vino> findAll(Integer pageNo) {
		return vinoRepository.findAll(PageRequest.of(pageNo,3));
	}

	@Override
	public Vino update(Vino vino) {
		 return vinoRepository.save(vino);
	}

	@Override
	public Vino delete(Long id) {
		Vino vino = vinoRepository.findOneById(id);
		if(vino != null) {
			vino.getTipVina().getVina().remove(vino);
			vino.getVinarija().getVina().remove(vino);
			vino.setTipVina(null);
			vino.setVinarija(null);
			vino = vinoRepository.save(vino);
			vinoRepository.delete(vino);
			return vino;
		}
		return null;
	}

	@Override
	public Page<Vino> find(Long vinarijaId, String ime, Integer pageNo) {
		if(ime == null) {
			ime = "%%";
		}else {
			ime = "%" + ime + "%";
		}
		if(vinarijaId == null) {
			return vinoRepository.findByImeLike(ime, PageRequest.of(pageNo,3));
		}

		return vinoRepository.findByVinarijaIdAndImeLike(vinarijaId, ime, PageRequest.of(pageNo,3));
	}

	@Override
	public List<Vino> findByVinarijaId(Long vinarijaId) {
		return vinoRepository.findByVinarijaId(vinarijaId);
	}

	@Override
	public Vino save(Vino vino) {
		return vinoRepository.save(vino);
	}
}
