package com.ufcg.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ufcg.si1.stategydescontos.Desconto;
import com.ufcg.si1.stategydescontos.SemDesconto;

@Entity
@Table(name = "tb_categoria")
public class Categoria implements Comparable<Categoria> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Transient
	private Desconto desconto;
	
	public Categoria() {
		this.nome = "";
		this.desconto = new SemDesconto();
	}
	
	public Categoria(String nome) {
		this.nome = nome;
		this.desconto = new SemDesconto();
	}

	public String getNome() {
		return nome;
	}

	public void setCategoria(String categoria) {
		this.nome = categoria;
	}

	public Desconto getDesconto() {
		return desconto;
	}

	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public int compareTo(Categoria categoria) {
		return this.nome.compareTo(categoria.getNome());
	}
	
	

}