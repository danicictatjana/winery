package com.ftninformatika.jwd.modul3.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.TipVina;

@Repository
public interface TipVinaRepository extends JpaRepository<TipVina,Long>{

	@Override
	List<TipVina> findAll();

	TipVina findOneById (Long id);

}
