package org.mce.teknoservice.repository;

import org.mce.teknoservice.domain.Impianto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contratto entity.
 */
public interface ImpiantoRepository extends JpaRepository<Impianto,Integer> {


	
}
