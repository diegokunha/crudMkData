package com.deeconsulting.crud.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class ClienteDto {

	private Long idCliente;
	@NotBlank
	private String nome;
	@NotBlank
	private Integer tipo;
	@NotBlank
	private String cpf;
	private String rg;
	@NotBlank
	private String cnpj;
	private String ie;
	private Date dataCriacao;
	private Boolean ativo;
	
	private Set<String> telefones = new HashSet<>();

	public ClienteDto(Long id, @NotBlank String nome, @NotBlank Integer tipo, @NotBlank String cpf, String rg,
			@NotBlank String cnpj, String ie, Date dataCriacao, Boolean ativo, Set<String> telefones) {
		super();
		this.idCliente = id;
		this.nome = nome;
		this.tipo = tipo;
		this.cpf = cpf;
		this.rg = rg;
		this.cnpj = cnpj;
		this.ie = ie;
		this.dataCriacao = dataCriacao;
		this.ativo = ativo;
		this.telefones = telefones;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

		
	
}
