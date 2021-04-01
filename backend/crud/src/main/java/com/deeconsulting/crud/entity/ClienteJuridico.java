package com.deeconsulting.crud.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "idCliente")
public class ClienteJuridico extends Cliente {

	private static final long serialVersionUID = 1649008225161764452L;

	private String cnpj;
	private String ie;

	public ClienteJuridico() {
	}

	public ClienteJuridico(String nome, Date dataCriacao, Boolean ativo, Integer tipo, String cnpj, String ie) {
		super(nome, dataCriacao, ativo, tipo);
		this.cnpj = cnpj;
		this.ie = ie;
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

}
