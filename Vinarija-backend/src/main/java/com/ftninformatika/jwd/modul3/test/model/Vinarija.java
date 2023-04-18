package com.ftninformatika.jwd.modul3.test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vinarija {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String ime;

	@Column
	private int godinaOsnivanja;

	@OneToMany(mappedBy = "vinarija", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Vino> vina = new ArrayList<>();

	public Vinarija() {
		super();
	}

	public Vinarija(Long id, String ime, int godinaOsnivanja) {
		super();
		this.id = id;
		this.ime = ime;
		this.godinaOsnivanja = godinaOsnivanja;
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

	public int getGodinaOsnivanja() {
		return godinaOsnivanja;
	}

	public void setGodinaOsnivanja(int godinaOsnivanja) {
		this.godinaOsnivanja = godinaOsnivanja;
	}

	public List<Vino> getVina() {
		return vina;
	}

	public void setVina(List<Vino> vina) {
		this.vina = vina;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Vinarija other = (Vinarija) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Vinarija [id=" + id + ", ime=" + ime + ", godinaOsnivanja=" + godinaOsnivanja + "]";
	}



}
