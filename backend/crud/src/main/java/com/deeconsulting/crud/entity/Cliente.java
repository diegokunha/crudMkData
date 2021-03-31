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
import javax.persistence.Table;

import com.deeconsulting.crud.enums.TipoCliente;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7882556046451557730L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer tipo;
	private String cpfOuCnpj;
	private String rgOuIe;
	private Date dataCadastro;
	private Boolean ativo;

	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>();

	public Cliente() {}
	
	public Cliente(String nome, String cpfOuCnpj, String rgOuIe, TipoCliente tipo) {
		super();
		this.nome = nome;
		this.cpfOuCnpj = cpfOuCnpj;
		this.rgOuIe = rgOuIe;
		this.tipo = (tipo == null) ? null : tipo.getCodigo();
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}

}
