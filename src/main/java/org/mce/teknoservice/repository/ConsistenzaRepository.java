package org.mce.teknoservice.repository;

import org.mce.teknoservice.domain.Consistenza;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface ConsistenzaRepository extends JpaRepository<Consistenza,Integer> {


	
}
