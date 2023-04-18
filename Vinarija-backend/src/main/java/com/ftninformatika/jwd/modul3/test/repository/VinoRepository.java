package com.ftninformatika.jwd.modul3.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Vino;

@Repository
public interface VinoRepository extends JpaRepository<Vino,Long>{

	Vino findOneById(Long id);

	List<Vino> findByVinarijaId(Long vinarijaId);

	Page<Vino> findByVinarijaIdAndImeLike(Long vinarijaId, String ime, Pageable pageable);

	Page<Vino> findByImeLike(String ime, Pageable pageable);

}
