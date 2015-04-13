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
 * Typeconsistenza generated by hbm2java
 */
@Entity
@Table(name = "t_typeconsistenza")
public class Typeconsistenza implements java.io.Serializable {

	private Integer typeConsistenzaId;
	private String descrizione;
	private String codice;
	@JsonIgnore
	private Set<Consistenza> consistenzas = new HashSet<Consistenza>(0);
	@JsonIgnore
	private Set<Typeimpianto> typeimpiantos = new HashSet<Typeimpianto>(0);

	public Typeconsistenza() {
	}

	public Typeconsistenza(String descrizione) {
		this.descrizione = descrizione;
	}

	public Typeconsistenza(String descrizione, String codice,
			Set<Consistenza> consistenzas, Set<Typeimpianto> typeimpiantos) {
		this.descrizione = descrizione;
		this.codice = codice;
		this.consistenzas = consistenzas;
		this.typeimpiantos = typeimpiantos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "typeConsistenzaId", unique = true, nullable = false)
	public Integer getTypeConsistenzaId() {
		return this.typeConsistenzaId;
	}

	public void setTypeConsistenzaId(Integer typeConsistenzaId) {
		this.typeConsistenzaId = typeConsistenzaId;
	}

	@Column(name = "descrizione", nullable = false, length = 50)
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "typeconsistenza")
	public Set<Consistenza> getConsistenzas() {
		return this.consistenzas;
	}

	public void setConsistenzas(Set<Consistenza> consistenzas) {
		this.consistenzas = consistenzas;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "typeconsistenza")
	public Set<Typeimpianto> getTypeimpiantos() {
		return this.typeimpiantos;
	}

	public void setTypeimpiantos(Set<Typeimpianto> typeimpiantos) {
		this.typeimpiantos = typeimpiantos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeConsistenzaId == null) ? 0 : typeConsistenzaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Typeconsistenza other = (Typeconsistenza) obj;
		if (typeConsistenzaId == null) {
			if (other.typeConsistenzaId != null)
				return false;
		} else if (!typeConsistenzaId.equals(other.typeConsistenzaId))
			return false;
		return true;
	}
	
	

}
