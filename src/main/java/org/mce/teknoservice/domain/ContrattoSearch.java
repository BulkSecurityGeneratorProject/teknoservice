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

public class ContrattoSearch implements Serializable {

	@JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime decorrenzaDateBegin;
    
	@JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime decorrenzaDateEnd;
	
	@JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime scadenzaDateBegin;
    
	@JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime scadenzaDateEnd;

    private BigDecimal importoBegin;
    
    private BigDecimal importoEnd;

    private Integer discountPercentBegin;
    
    private Integer discountPercentEnd;

	public DateTime getDecorrenzaDateBegin() {
		return decorrenzaDateBegin;
	}

	public void setDecorrenzaDateBegin(DateTime decorrenzaDateBegin) {
		this.decorrenzaDateBegin = decorrenzaDateBegin;
	}

	public DateTime getScadenzaDateEnd() {
		return scadenzaDateEnd;
	}

	public void setScadenzaDateEnd(DateTime scadenzaDateEnd) {
		this.scadenzaDateEnd = scadenzaDateEnd;
	}

	public BigDecimal getImportoBegin() {
		return importoBegin;
	}

	public void setImportoBegin(BigDecimal importoBegin) {
		this.importoBegin = importoBegin;
	}

	public BigDecimal getImportoEnd() {
		return importoEnd;
	}

	public void setImportoEnd(BigDecimal importoEnd) {
		this.importoEnd = importoEnd;
	}

	public Integer getDiscountPercentBegin() {
		return discountPercentBegin;
	}

	public void setDiscountPercentBegin(Integer discountPercentBegin) {
		this.discountPercentBegin = discountPercentBegin;
	}

	public Integer getDiscountPercentEnd() {
		return discountPercentEnd;
	}

	public void setDiscountPercentEnd(Integer discountPercentEnd) {
		this.discountPercentEnd = discountPercentEnd;
	}

	public DateTime getDecorrenzaDateEnd() {
		return decorrenzaDateEnd;
	}

	public void setDecorrenzaDateEnd(DateTime decorrenzaDateEnd) {
		this.decorrenzaDateEnd = decorrenzaDateEnd;
	}

	public DateTime getScadenzaDateBegin() {
		return scadenzaDateBegin;
	}

	public void setScadenzaDateBegin(DateTime scadenzaDateBegin) {
		this.scadenzaDateBegin = scadenzaDateBegin;
	}
    
    
    
}
