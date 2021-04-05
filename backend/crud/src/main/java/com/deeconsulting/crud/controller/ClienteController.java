package com.deeconsulting.crud.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deeconsulting.crud.dto.ClienteDto;
import com.deeconsulting.crud.dto.Message;
import com.deeconsulting.crud.entity.Cliente;
import com.deeconsulting.crud.entity.ClienteFisico;
import com.deeconsulting.crud.entity.ClienteJuridico;
import com.deeconsulting.crud.service.ClienteService;

@RestController
@RequestMapping("/mdkdata/crud")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

	private final ClienteService service;

	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping("/lista")
	public ResponseEntity<List<Cliente>> getList() {
		List<Cliente> list = service.getList();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@GetMapping("detailId/{id}")
	public ResponseEntity<Cliente> detailById(@PathVariable("id") Long id){
		if(!service.existsById(id)) {
            return new ResponseEntity(new Message("Cliente não encontrado!"), HttpStatus.NOT_FOUND);
		}else {
			Cliente cliente = service.getById(id).get();
			return new ResponseEntity(cliente, HttpStatus.OK);
		}
	}
	
	@GetMapping("/detailNome/{nome}")
	public ResponseEntity<List<Cliente>> detailNome(@PathVariable("nome") String nome){
		
		if(!service.existsByNome(nome)) {
            return new ResponseEntity(new Message("Cliente não encontrado!"), HttpStatus.NOT_FOUND);
		}else {
			List<Cliente> clientes = service.getByNome(nome);
			return new ResponseEntity(clientes, HttpStatus.OK);
		}
	}
	
	@GetMapping("/detailAtivo/{ativo}")
	public ResponseEntity<List<Cliente>> detailAtivo(@PathVariable("ativo") boolean ativo){
		if(!service.existsByAtivo(ativo)) {
            return new ResponseEntity(new Message("Não foi encontrado clientes com esse status!"), HttpStatus.NOT_FOUND);
		}else {
			List<Cliente> clientes = service.getByAtivo(ativo);
			return new ResponseEntity(clientes, HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		ClienteFisico clienteFisico = null;
		ClienteJuridico clienteJuridico = null;

		if (StringUtils.isBlank(clienteDto.getNome()))
			return new ResponseEntity(new Message("O campo nome é obrigatório!"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(clienteDto.getTipo().toString()))
			return new ResponseEntity(new Message("O campo tipo é obrigatório!"), HttpStatus.BAD_REQUEST);

		if (clienteDto.getTipo() == 1) {
			if (StringUtils.isBlank(clienteDto.getCpf()))
				return new ResponseEntity(new Message("O campo cpf é obrigatório!"), HttpStatus.BAD_REQUEST);
			if(service.existsByCpf(clienteDto.getCpf()))
				return new ResponseEntity(new Message("Cliente já cadastrado na base de dados!"), HttpStatus.BAD_REQUEST);
			
			clienteFisico = new ClienteFisico(clienteDto.getNome(), new Date(), true, clienteDto.getTipo(), clienteDto.getCpf(), clienteDto.getRg());
			clienteFisico.getTelefones().addAll(clienteDto.getTelefones());
			
			cliente = clienteFisico;
			service.save(cliente);
		}

		if (clienteDto.getTipo() == 2) {
			if (StringUtils.isBlank(clienteDto.getCnpj()))
				return new ResponseEntity(new Message("O campo cnpj é obrigatório!"), HttpStatus.BAD_REQUEST);
			if(service.existsByCnpj(clienteDto.getCnpj()))
				return new ResponseEntity(new Message("Cliente já cadastrado na base de dados!"), HttpStatus.BAD_REQUEST);
			
			clienteJuridico = new ClienteJuridico(clienteDto.getNome(), new Date(), true, clienteDto.getTipo(), clienteDto.getCnpj(), clienteDto.getIe());
			clienteJuridico.getTelefones().addAll(clienteDto.getTelefones());
			
			cliente = clienteJuridico;
			service.save(cliente);
		}

		return new ResponseEntity(new Message("Cliente cadastrado com sucesso!"), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody ClienteDto clienteDto){
		
		if(!service.existsById(id))
            return new ResponseEntity(new Message("Cliente não cadastrado na base de dados!"), HttpStatus.NOT_FOUND);
        
        if (StringUtils.isBlank(clienteDto.getNome()))
			return new ResponseEntity(new Message("O campo nome é obrigatório!"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(clienteDto.getTipo().toString()))
			return new ResponseEntity(new Message("O campo tipo é obrigatório!"), HttpStatus.BAD_REQUEST);

        if (clienteDto.getTipo() == 1) {
			if (StringUtils.isBlank(clienteDto.getCpf()))
				return new ResponseEntity(new Message("O campo cpf é obrigatório!"), HttpStatus.BAD_REQUEST);
			if(service.existsByCpf(clienteDto.getCpf()) && service.findByCpf(clienteDto.getCpf()).get().getIdCliente() != id)
				return new ResponseEntity(new Message("Cliente já cadastrado na base de dados!"), HttpStatus.BAD_REQUEST);

			ClienteFisico clienteFisico = new ClienteFisico(clienteDto.getNome(), clienteDto.getDataCriacao(), clienteDto.getAtivo(), clienteDto.getTipo(), clienteDto.getCpf(), clienteDto.getRg());
			clienteFisico.getTelefones().addAll(clienteDto.getTelefones());
			
			Cliente cliente = clienteFisico;
			cliente.setIdCliente(id);
			service.uptade(cliente);
		}
        
        if (clienteDto.getTipo() == 2) {
			if (StringUtils.isBlank(clienteDto.getCnpj()))
				return new ResponseEntity(new Message("O campo cnpj é obrigatório!"), HttpStatus.BAD_REQUEST);
			if(service.existsByCnpj(clienteDto.getCnpj()) && service.findByCnpj(clienteDto.getCnpj()).get().getIdCliente() != id)
				return new ResponseEntity(new Message("Cliente já cadastrado na base de dados!"), HttpStatus.BAD_REQUEST);
			
			ClienteJuridico clienteJuridico = new ClienteJuridico(clienteDto.getNome(), clienteDto.getDataCriacao(), clienteDto.getAtivo(), clienteDto.getTipo(), clienteDto.getCnpj(), clienteDto.getIe());
			clienteJuridico.getTelefones().addAll(clienteDto.getTelefones());
			
			Cliente cliente = clienteJuridico;
			cliente.setIdCliente(id);
			service.uptade(cliente);
		}
        
        return new ResponseEntity(new Message("Cliente atualizado com sucesso!"), HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if(!service.existsById(id))
            return new ResponseEntity(new Message("Cliente não cadastrado na base de dados!"), HttpStatus.NOT_FOUND);
        service.delete(id);
        return new ResponseEntity(new Message("Cliente excluído com sucesso!"), HttpStatus.OK);
    }

}
