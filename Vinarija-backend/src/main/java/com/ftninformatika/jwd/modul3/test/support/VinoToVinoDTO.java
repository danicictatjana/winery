package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Vino;
import com.ftninformatika.jwd.modul3.test.web.dto.VinoDTO;

@Component
public class VinoToVinoDTO implements Converter<Vino, VinoDTO>{

	@Autowired
    private TipVinaToTipVinaDTO tipVinaToTipVinaDTO;

	@Autowired
    private VinarijaToVinarijaDTO vinarijaToVinarijaDTO;

	@Override
	public VinoDTO convert(Vino vino) {
		VinoDTO vinoDTO = new VinoDTO();
		vinoDTO.setId(vino.getId());
		vinoDTO.setIme(vino.getIme());
		vinoDTO.setOpis(vino.getOpis());
		vinoDTO.setGodinaProizvodnje(vino.getGodinaProizvodnje());
		vinoDTO.setCenaFlase(vino.getCenaFlase());
		vinoDTO.setBrojDostupnihFlasa(vino.getBrojDostupnihFlasa());
		vinoDTO.setTipVinaId(vino.getTipVina().getId());
		vinoDTO.setTipVinaIme(vino.getTipVina().getIme());
		vinoDTO.setVinarijaId(vino.getVinarija().getId());
		vinoDTO.setVinarijaIme(vino.getVinarija().getIme());
        return vinoDTO;
	}

	public List<VinoDTO> convert(List<Vino> vina){
        List<VinoDTO> vinaDTO = new ArrayList<>();

        for(Vino vino : vina) {
        	vinaDTO.add(convert(vino));
        }

        return vinaDTO;
    }

}
