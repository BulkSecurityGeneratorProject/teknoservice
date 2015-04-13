package org.mce.teknoservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.mce.teknoservice.domain.util.CustomDateTimeDeserializer;
import org.mce.teknoservice.domain.util.CustomDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A Contratto.
 */
@Entity
@Table(name = "T_CONTRATTO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contratto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "decorrenza_date")
    private DateTime decorrenzaDate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "scadenza_date")
    private DateTime scadenzaDate;

    @Column(name = "importo", precision=10, scale=2)
    private BigDecimal importo;

    @Column(name = "discount_percent")
    private Integer discountPercent;
    
    @Column(name = "indirizzo_immobile")
    private String indirizzoImmobile;

    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "typeOggettoId")
    private Typeoggetto typeoggetto;
    
    @ManyToOne
    @JoinColumn(name = "typeContrattoId")
    private Typecontratto typecontratto;
    
    @ManyToOne
    @JoinColumn(name = "typeModalitaPagamentoId")
    private Typemodalitapagamento typemodalitapagamento;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contratto", cascade={CascadeType.ALL})
    //@JsonManagedReference("consistenza-contratto")
    @JsonManagedReference("contratto.consitenzas")
    //@Fetch(FetchMode.JOIN)
    private Set<Consistenza> consistenzas = new HashSet<Consistenza>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getDecorrenzaDate() {
        return decorrenzaDate;
    }

    public void setDecorrenzaDate(DateTime decorrenzaDate) {
        this.decorrenzaDate = decorrenzaDate;
    }

    public DateTime getScadenzaDate() {
        return scadenzaDate;
    }

    public void setScadenzaDate(DateTime scadenzaDate) {
        this.scadenzaDate = scadenzaDate;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }
    
    public String getIndirizzoImmobile() {
  		return indirizzoImmobile;
  	}

  	public void setIndirizzoImmobile(String indirizzoImmobile) {
  		this.indirizzoImmobile = indirizzoImmobile;
  	}


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
	public Typeoggetto getTypeoggetto() {
		return typeoggetto;
	}

	public void setTypeoggetto(Typeoggetto typeoggetto) {
		this.typeoggetto = typeoggetto;
	}

	public Typecontratto getTypecontratto() {
		return typecontratto;
	}

	public void setTypecontratto(Typecontratto typecontratto) {
		this.typecontratto = typecontratto;
	}
	
	public Typemodalitapagamento getTypemodalitapagamento() {
		return typemodalitapagamento;
	}

	public void setTypemodalitapagamento(Typemodalitapagamento typemodalitapagamento) {
		this.typemodalitapagamento = typemodalitapagamento;
	}
	
	public Set<Consistenza> getConsistenzas() {
		return this.consistenzas;
	}

	public void setConsistenzas(Set<Consistenza> consistenzas) {
		this.consistenzas = consistenzas;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contratto contratto = (Contratto) o;

        if ( ! Objects.equals(id, contratto.id)) return false;

        return true;
    }

	@Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Contratto{" +
                "id=" + id +
                ", decorrenzaDate='" + decorrenzaDate + "'" +
                ", scadenzaDate='" + scadenzaDate + "'" +
                ", importo='" + importo + "'" +
                ", discountPercent='" + discountPercent + "'" +
                '}';
    }
}
