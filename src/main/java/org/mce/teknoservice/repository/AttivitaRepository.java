package org.mce.teknoservice.repository;

import org.mce.teknoservice.domain.Attivita;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface AttivitaRepository extends JpaRepository<Attivita,Integer> {


	
}
