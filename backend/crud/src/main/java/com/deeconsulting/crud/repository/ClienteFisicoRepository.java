package com.deeconsulting.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deeconsulting.crud.entity.ClienteFisico;

@Repository
public interface ClienteFisicoRepository extends JpaRepository<ClienteFisico, Long>{

	boolean existsByCpf(String cpf);

	Optional<ClienteFisico> findByCpf(String cpf);
	
}
