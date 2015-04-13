package org.mce.teknoservice.domain;

// Generated 18-nov-2013 12.05.48 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Typeoggetto generated by hbm2java
 */
@Entity
@Table(name = "t_typeoggetto")
public class Typeoggetto implements java.io.Serializable {

	private Integer typeOggettoId;
	private String descrizione;
	private String codice;
	@JsonIgnore
	private Set<Contratto> contrattos = new HashSet<Contratto>(0);

	public Typeoggetto() {
	}

	public Typeoggetto(String descrizione, String codice,
			Set<Contratto> contrattos) {
		this.descrizione = descrizione;
		this.codice = codice;
		this.contrattos = contrattos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "typeOggettoId", unique = true, nullable = false)
	public Integer getTypeOggettoId() {
		return this.typeOggettoId;
	}

	public void setTypeOggettoId(Integer typeOggettoId) {
		this.typeOggettoId = typeOggettoId;
	}

	@Column(name = "descrizione")
	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Column(name = "codice", length = 5)
	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "typeoggetto")
	public Set<Contratto> getContrattos() {
		return this.contrattos;
	}

	public void setContrattos(Set<Contratto> contrattos) {
		this.contrattos = contrattos;
	}

}
