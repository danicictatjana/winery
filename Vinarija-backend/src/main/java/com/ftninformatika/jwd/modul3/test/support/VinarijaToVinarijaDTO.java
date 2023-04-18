package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Vinarija;
import com.ftninformatika.jwd.modul3.test.web.dto.VinarijaDTO;

@Component
public class VinarijaToVinarijaDTO implements Converter<Vinarija, VinarijaDTO> {

	@Override
	public VinarijaDTO convert(Vinarija vinarija) {
		VinarijaDTO dto = new VinarijaDTO();
        dto.setId(vinarija.getId());
        dto.setIme(vinarija.getIme());
        dto.setGodinaOsnivanja(vinarija.getGodinaOsnivanja());
        return dto;
	}

	public List<VinarijaDTO> convert(List<Vinarija> vinarije){
        List<VinarijaDTO> vinarijeDto = new ArrayList<>();

        for(Vinarija vinarija : vinarije) {
        	vinarijeDto.add(convert(vinarija));
        }

        return vinarijeDto;
    }

}
