package org.mce.teknoservice.repository;

import org.mce.teknoservice.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Cliente entity.
 */
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
	
	@Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.contrattos contr WHERE c.id = :id")
 	public Cliente findById(@Param("id") Long id);

}
