package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.TipVina;
import com.ftninformatika.jwd.modul3.test.web.dto.TipVinaDTO;

@Component
public class TipVinaToTipVinaDTO implements Converter<TipVina, TipVinaDTO>{

	@Override
	public TipVinaDTO convert(TipVina tipVina) {
		TipVinaDTO dto = new TipVinaDTO();
        dto.setId(tipVina.getId());
        dto.setIme(tipVina.getIme());
        return dto;
	}

	public List<TipVinaDTO> convert(List<TipVina> tipoviVina){
        List<TipVinaDTO> tipoviTipVinaDto = new ArrayList<>();

        for(TipVina tipVina : tipoviVina) {
        	tipoviTipVinaDto.add(convert(tipVina));
        }

        return tipoviTipVinaDto;
    }
}
