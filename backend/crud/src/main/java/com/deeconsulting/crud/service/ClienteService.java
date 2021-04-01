package com.deeconsulting.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deeconsulting.crud.entity.Cliente;
import com.deeconsulting.crud.entity.ClienteFisico;
import com.deeconsulting.crud.entity.ClienteJuridico;
import com.deeconsulting.crud.exceptions.ResourceNotFoundException;
import com.deeconsulting.crud.repository.ClienteFisicoRepository;
import com.deeconsulting.crud.repository.ClienteJuridicoRepository;
import com.deeconsulting.crud.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository repository;
	private final ClienteFisicoRepository fRepository;
	private final ClienteJuridicoRepository jRepository;

	@Autowired
	public ClienteService(ClienteRepository repository, ClienteFisicoRepository fRepository, ClienteJuridicoRepository jRepository) {
		this.repository = repository;
		this.jRepository = jRepository;
		this.fRepository = fRepository;
	}
	
	public List<Cliente> getList(){
		return repository.findAll();
	}
	
	public Optional<Cliente> getById(Long id){
		return repository.findById(id);
	}
	
	public Optional<Cliente> getByNome(String nome){
        return repository.findByNomeContainingKeywordAnywhere(nome);
    }
	
	public Optional<ClienteFisico> findByCpf(String cpf){
        return fRepository.findByCpf(cpf);
    }
	
	public Optional<ClienteJuridico> findByCnpj(String cnpj){
        return jRepository.findByCnpj(cnpj);
    }
	
	public Optional<Cliente> getByAtivo(boolean ativo){
        return repository.findByAtivo(ativo);
    }
	
	public void save(Cliente cliente) {
		repository.save(cliente);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Cliente uptade(Cliente cliente) {
		final Optional<Cliente> oCliente = repository.findById(cliente.getIdCliente());
		
		if(!oCliente.isPresent())
			new ResourceNotFoundException("No records found for this ID"); 
		
		return repository.save(cliente);
	}
	
	public boolean existsById(Long id){
        return repository.existsById(id);
    }
	
	public boolean existsByCpf(String cpf){
        return fRepository.existsByCpf(cpf);
    }
	
	public boolean existsByCnpj(String cnpj){
        return jRepository.existsByCnpj(cnpj);
    }
	
	public boolean existsByNome(String nome){
        return repository.existsByNome(nome);
    }
	
	public boolean existsByAtivo(boolean ativo) {
		return repository.existsByAtivo(ativo);
	}

}
