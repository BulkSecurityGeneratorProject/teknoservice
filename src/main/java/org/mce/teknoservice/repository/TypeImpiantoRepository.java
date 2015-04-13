package org.mce.teknoservice.repository;

import java.util.List;

import org.mce.teknoservice.domain.Typeconsistenza;
import org.mce.teknoservice.domain.Typeimpianto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface TypeImpiantoRepository extends JpaRepository<Typeimpianto,Integer> {

	@Query("SELECT timp FROM Typeimpianto timp "
			+ "LEFT JOIN FETCH timp.typeinterventos tintervs "
			+ "LEFT JOIN FETCH tintervs.typeattivitas tatts "
			+ "WHERE timp.typeImpiantoId = :id")
 	public Typeimpianto findById(@Param("id") Integer id);
	
	public List<Typeimpianto> findByTypeconsistenza(Typeconsistenza typeConsistenza);
	
}
