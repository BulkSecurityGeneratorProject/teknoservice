package org.mce.teknoservice.repository;

import org.mce.teknoservice.domain.Typeconsistenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface TypeConsistenzaRepository extends JpaRepository<Typeconsistenza,Integer> {

	@Query("SELECT tc FROM Typeconsistenza tc "
			+ "LEFT JOIN FETCH tc.typeimpiantos timps "
			+ "LEFT JOIN FETCH timps.typeinterventos tinterv "
			+ "LEFT JOIN FETCH tinterv.typeattivitas tatt "
			+ "WHERE tc.typeConsistenzaId = :id")
 	public Typeconsistenza findById(@Param("id") Integer id);
	
}
