package com.deeconsulting.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deeconsulting.crud.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query(value = "SELECT * FROM cliente c where c.nome like %?1 or c.ativo = ?2")
	List<Cliente> findByNomeOrAtivo(String nome, boolean ativo);
	
	boolean existByNome(String nome);
	
	List<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
