package com.ufcg.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ufcg.si1.desconto.Desconto;
import com.ufcg.si1.desconto.SemDesconto;

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
	
	/**
         * Construtor sem parâmetro da classe Categoria
         */
	public Categoria() {
		this.nome = "";
		this.desconto = new SemDesconto();
	}
	
        /**
         * Construtor com parametro nome
         * @param nome  
         */
        
	public Categoria(String nome) {
		this.nome = nome;
		this.desconto = new SemDesconto();
	}

        /**
         * Retorna nome da Categoria
         * @return nome
         */
	public String getNome() {
		return nome;
	}

        /**
         * Altera o nome da categoria
         * 
         * @param categoria nome a ser alterado
         */
	public void setCategoria(String categoria) {
		this.nome = categoria;
	}

        /**
         * Retorna o desconto da categoria
         * @return desconto
         */
	public Desconto getDesconto() {
		return desconto;
	}
        /**
         * Altera o desconto
         * @param desconto desconto a ser alterado
         */
	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}

        /**
         * Informa o hashcode unico da categoria
         * @return inteiro hashcode
         */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

        /**
         * Compara a categoria com o objeto informado
         * 
         * @param obj Objeto a ser comparado
         * @return retorna true se forem iguais, falso caso contrário
         */
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
        /**
         * Compara as categorias
         * 
         * @param categoria a ser comparada
         * @return retorna 0 caso iguais, ou qualquer valor caso sejam diferentes
         */
	public int compareTo(Categoria categoria) {
		return this.nome.compareTo(categoria.getNome());
	}
	
	

}
