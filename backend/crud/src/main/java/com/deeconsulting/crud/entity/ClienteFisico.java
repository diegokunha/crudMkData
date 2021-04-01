package com.deeconsulting.crud.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="idCliente")
public class ClienteFisico extends Cliente{

	private static final long serialVersionUID = -204181503591155145L;
	
	private String cpf;
	private String rg;
		
	public ClienteFisico() {}
	
	public ClienteFisico(String nome, Date dataCriacao, Boolean ativo, Integer tipo, String cpf, String rg) {
		super(nome, dataCriacao, ativo, tipo);
		this.cpf = cpf;
		this.rg = rg;
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
	
}
