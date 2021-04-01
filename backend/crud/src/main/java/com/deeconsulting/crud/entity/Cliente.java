package com.deeconsulting.crud.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.deeconsulting.crud.entity.enums.TipoCliente;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente implements Serializable{

	private static final long serialVersionUID = 4601871529813801129L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	private String nome;
	private Integer tipo;
	private Date dataCriacao;
	private Boolean ativo;
	
	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>();
	
	public Cliente() {}
	
	public Cliente(String nome, Date dataCriacao, Boolean ativo, Integer tipo) {
		super();
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.ativo = ativo;
		this.tipo = tipo;
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

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}
	
}
