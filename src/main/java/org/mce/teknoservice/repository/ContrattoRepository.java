package org.mce.teknoservice.repository;

import java.util.List;

import org.mce.teknoservice.domain.Contratto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface ContrattoRepository extends JpaRepository<Contratto,Long>, JpaSpecificationExecutor  {

	@Query("SELECT c FROM Contratto c"
			+ " LEFT JOIN FETCH c.consistenzas cons"
			+ " LEFT JOIN FETCH cons.impiantos imps"
			+ " LEFT JOIN FETCH imps.interventos ints"
			+ " LEFT JOIN FETCH ints.attivitas atts"
			+ " WHERE c.id = :id")
	//@EntityGraph(value = "Contratto.consistenzas", type = EntityGraphType.LOAD)
 	public Contratto findById(@Param("id") Long id);
	
	
	@Query("SELECT count(c), sum(c.importo), c.scadenzaDate"
			+ " FROM Contratto c"
			+ " GROUP BY year(c.scadenzaDate), month(c.scadenzaDate) ")
 	public List<Object[]> countSumImportoGroupingByMonthScadenza();
	
	
}
