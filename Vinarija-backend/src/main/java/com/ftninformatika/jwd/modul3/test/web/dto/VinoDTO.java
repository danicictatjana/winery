package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class VinoDTO {

	private Long id;

	private String ime;

	@Size(max = 120)
	private String opis;

	@Positive(message = "Godina proizvodnje nije pozitivan broj.")
	private int godinaProizvodnje;

	@Positive(message = "Cena flase nije pozitivan broj.")
	private int cenaFlase;

	private int brojDostupnihFlasa;

	private Long tipVinaId;

    private String tipVinaIme;

    private Long vinarijaId;

    private String vinarijaIme;

	public Long getTipVinaId() {
		return tipVinaId;
	}

	public void setTipVinaId(Long tipVinaId) {
		this.tipVinaId = tipVinaId;
	}

	public String getTipVinaIme() {
		return tipVinaIme;
	}

	public void setTipVinaIme(String tipVinaIme) {
		this.tipVinaIme = tipVinaIme;
	}

	public Long getVinarijaId() {
		return vinarijaId;
	}

	public void setVinarijaId(Long vinarijaId) {
		this.vinarijaId = vinarijaId;
	}

	public String getVinarijaIme() {
		return vinarijaIme;
	}

	public void setVinarijaIme(String vinarijaIme) {
		this.vinarijaIme = vinarijaIme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public int getCenaFlase() {
		return cenaFlase;
	}

	public void setCenaFlase(int cenaFlase) {
		this.cenaFlase = cenaFlase;
	}

	public int getBrojDostupnihFlasa() {
		return brojDostupnihFlasa;
	}

	public void setBrojDostupnihFlasa(int brojDostupnihFlasa) {
		this.brojDostupnihFlasa = brojDostupnihFlasa;
	}

}
