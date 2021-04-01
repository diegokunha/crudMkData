package com.deeconsulting.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deeconsulting.crud.entity.ClienteJuridico;

@Repository
public interface ClienteJuridicoRepository extends JpaRepository<ClienteJuridico, Long>{

	boolean existsByCnpj(String cnpj);

	Optional<ClienteJuridico> findByCnpj(String cnpj);
	
}
