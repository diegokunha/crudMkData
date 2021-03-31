package com.deeconsulting.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deeconsulting.crud.entity.Cliente;
import com.deeconsulting.crud.exceptions.ResourceNotFoundException;
import com.deeconsulting.crud.repositories.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository repository;

	@Autowired
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public List<Cliente> findByNomeOrAtivo(String nome, boolean ativo) {
		return repository.findByNomeOrAtivo(nome, ativo);
	}
	
	public List<Cliente> findByCpfOuCnpj(String cpfOuCnpj) {
		return repository.findByCpfOuCnpj(cpfOuCnpj);
	}
	
	public Optional<Cliente> findById(Long id){
		return repository.findById(id);
	}

	public void save(Cliente cliente) {
		repository.save(cliente);
	}
	
	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar esse ID"));
		
		repository.delete(entity);
	}

	public Cliente update(Cliente cliente) {
		final Optional<Cliente> optionalProduto = repository.findById(cliente.getId());

		if (!optionalProduto.isPresent()) {
			new ResourceNotFoundException("Objeto não encontrado! Id: " + cliente.getId());
		}

		return repository.save(cliente);
	}
	
	public boolean existsByNome(String nome) {
		return repository.existByNome(nome);
	}
	
	public boolean existsById(Long id) {
		return repository.findById(id) != null;
	}
}
