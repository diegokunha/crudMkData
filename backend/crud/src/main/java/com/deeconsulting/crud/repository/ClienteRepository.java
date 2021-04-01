package com.deeconsulting.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deeconsulting.crud.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("select c from Cliente c where c.nome LIKE %?1%")
	Optional<Cliente> findByNomeContainingKeywordAnywhere(String nome);
	
	Optional<Cliente> findByAtivo(boolean ativo);
	
	boolean existsByNome(String nome);
	
	boolean existsByAtivo(boolean ativo);
	
}
