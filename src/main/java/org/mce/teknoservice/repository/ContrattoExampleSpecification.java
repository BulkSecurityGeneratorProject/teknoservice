package org.mce.teknoservice.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.mce.teknoservice.domain.Contratto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ContrattoExampleSpecification implements Specification<Contratto> {

	  private Contratto example;

	  
	  public ContrattoExampleSpecification() {}
	  
	  public ContrattoExampleSpecification(Contratto example) {
	    this.example = example;
	  }
	  
	  public void setEnitityExample(Contratto example) {
		    this.example = example;
	  }

	  @Override
	  public Predicate toPredicate(Root<Contratto> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
	    List<Predicate> predicates = new ArrayList<>();

	   /* if (StringUtils.isNotBlank(example.getCliente().getCognome())) {
	      predicates.add(cb.like(cb.lower(root.get("cliente").get("cognome")), example.getCliente().getCognome().toLowerCase() + "%"));
	    }*/
	    
	    // date scadenza/decorrenza
	    if (example.getSearch().getScadenzaDateBegin() != null && example.getSearch().getScadenzaDateEnd() != null) {
	      predicates.add(cb.between(root.get("scadenzaDate"), example.getSearch().getScadenzaDateBegin(), example.getSearch().getScadenzaDateEnd()));
	    }else{
	    	if (example.getSearch().getScadenzaDateBegin() != null) {
	    		predicates.add(cb.greaterThanOrEqualTo(root.get("scadenzaDate"), example.getSearch().getScadenzaDateBegin()));
	    	}
	    	if (example.getSearch().getScadenzaDateEnd() != null) {
	    		predicates.add(cb.lessThanOrEqualTo(root.get("scadenzaDate"), example.getSearch().getScadenzaDateEnd()));
	    	}
	    }
	    
	    // importi
	    if (example.getSearch().getImportoBegin() != null && example.getSearch().getImportoEnd() != null) {
		      predicates.add(cb.between(root.get("importo"), example.getSearch().getImportoBegin(), example.getSearch().getImportoEnd()));
		    }else{
		    	if (example.getSearch().getImportoBegin() != null) {
		    		predicates.add(cb.ge(root.get("importo"), example.getSearch().getImportoBegin()));
		    	}
		    	if (example.getSearch().getImportoEnd() != null) {
		    		predicates.add(cb.le(root.get("importo"), example.getSearch().getImportoEnd()));
		    	}
		    }

	    return andTogether(predicates, cb);
	  }

	  private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
	    return cb.and(predicates.toArray(new Predicate[0]));
	  }
	}