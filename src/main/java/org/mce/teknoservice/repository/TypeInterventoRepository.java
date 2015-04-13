package org.mce.teknoservice.repository;

import java.util.List;

import org.mce.teknoservice.domain.Typeimpianto;
import org.mce.teknoservice.domain.Typeintervento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface TypeInterventoRepository extends JpaRepository<Typeintervento,Integer> {

	@Query("SELECT tinterv FROM Typeintervento tinterv "
			+ "LEFT JOIN FETCH tinterv.typeattivitas tatts "
			+ "WHERE tinterv.typeInterventoId = :id")
 	public Typeintervento findById(@Param("id") Integer id);
	
	
	public List<Typeintervento> findByTypeimpianto(Typeimpianto typeImpianto);
	

	
}
