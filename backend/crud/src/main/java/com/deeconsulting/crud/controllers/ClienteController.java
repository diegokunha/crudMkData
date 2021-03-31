package com.deeconsulting.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deeconsulting.crud.entity.Cliente;
import com.deeconsulting.crud.entity.dto.ClienteDto;
import com.deeconsulting.crud.exceptions.Message;
import com.deeconsulting.crud.services.ClienteService;


@RestController
@RequestMapping(name = "/mkdata/crud")
public class ClienteController {

	private final ClienteService service;
	
	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Cliente>> list(){
		List<Cliente> clientes = service.findAll();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{nome}, {ativo}")
	public ResponseEntity<Cliente> getByNomeOrAtivo(@PathVariable("nome") String nome, @PathVariable("ativo") Boolean ativo){
		if(!service.existsByNome(nome)) 
			return new ResponseEntity(new Message("Não existe"), HttpStatus.NOT_FOUND);
			
		Cliente cliente = service.findByNomeOrAtivo(nome, ativo).get(0);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){		
		if(service.findByCpfOuCnpj(clienteDto.getCpfOuCnpj()) != null)
			return new ResponseEntity(new Message("Cliente já cadastrado!!"), HttpStatus.BAD_REQUEST);
		if(clienteDto.getNome() == null)
			return new ResponseEntity(new Message("Preenchimento do nome é obrigatório!!"), HttpStatus.BAD_REQUEST);
		if(clienteDto.getTipo() == null)
			return new ResponseEntity(new Message("Preenchimento do tipo é obrigatório!!"), HttpStatus.BAD_REQUEST);
		if(clienteDto.getCpfOuCnpj() == null)
			return new ResponseEntity(new Message("Preenchimento do CPF/CNPJ é obrigatório!!"), HttpStatus.BAD_REQUEST);
		Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getCpfOuCnpj(), clienteDto.getRgOuIe(), clienteDto.getTipo());
		service.save(cliente);
		return new ResponseEntity<>(new Message("Cliente cadastrado!!"), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Cliente cliente){
		if(service.findByCpfOuCnpj(cliente.getCpfOuCnpj()) != null)
			return new ResponseEntity(new Message("Cliente já cadastrado!!"), HttpStatus.BAD_REQUEST);
		if(cliente.getNome() == null)
			return new ResponseEntity(new Message("Preenchimento do nome é obrigatório!!"), HttpStatus.BAD_REQUEST);
		if(cliente.getTipo() == null)
			return new ResponseEntity(new Message("Preenchimento do tipo é obrigatório!!"), HttpStatus.BAD_REQUEST);
		if(cliente.getCpfOuCnpj() == null)
			return new ResponseEntity(new Message("Preenchimento do CPF/CNPJ é obrigatório!!"), HttpStatus.BAD_REQUEST);
		service.update(cliente);
		return new ResponseEntity<>(new Message("Cliente atualizado!!"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(!service.existsById(id))
			return new ResponseEntity(new Message("Não existe"), HttpStatus.NOT_FOUND);
		service.delete(id);
		return new ResponseEntity<>(new Message("Cliente excluído!!"), HttpStatus.OK);
	}
}
