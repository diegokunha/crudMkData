package com.deeconsulting.crud.entity.dto;

import java.util.Date;

import com.deeconsulting.crud.enums.TipoCliente;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClienteDto {

	private Long id;
	private String nome;
	private Integer tipo;
	private String cpfOuCnpj;
	private String rgOuIe;
	private Date dataCadastro;
	private Boolean ativo;
	
	public ClienteDto(Long id, String nome, Integer tipo, String cpfOuCnpj, String rgOuIe, Date dataCadastro,
			Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.cpfOuCnpj = cpfOuCnpj;
		this.rgOuIe = rgOuIe;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}
	
	
}
