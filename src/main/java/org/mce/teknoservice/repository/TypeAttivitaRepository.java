package org.mce.teknoservice.repository;

import java.util.List;

import org.mce.teknoservice.domain.Typeattivita;
import org.mce.teknoservice.domain.Typeintervento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface TypeAttivitaRepository extends JpaRepository<Typeattivita,Integer> {

	@Query("SELECT tatt FROM Typeattivita tatt "
			+ "WHERE tatt.typeAttivitaId = :id")
 	public Typeattivita findById(@Param("id") Integer id);
	
	
	public List<Typeattivita> findByTypeintervento(Typeintervento typeIintervento);
	
}
