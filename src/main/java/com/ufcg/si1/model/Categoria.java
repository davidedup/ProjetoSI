package com.ufcg.si1.model;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import exceptions.ObjetoInexistenteException;

@Entity
@Table(name = "tb_categoria")
public class Categoria implements Comparable<Categoria> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "desconto")
	private BigDecimal desconto;
	
	/**
         * Construtor sem parâmetro da classe Categoria
	 * @throws ObjetoInexistenteException 
         */
	public Categoria() throws ObjetoInexistenteException {
		this.nome = "";
		this.desconto = this.criaDesconto("sem");
	}
	
        /**
         * Construtor com parametro nome
         * @param nome  
         * @throws ObjetoInexistenteException 
         */
        
	public Categoria(String nome) throws ObjetoInexistenteException {
		this.nome = nome;
		this.desconto = this.criaDesconto("sem");
	}
	
	/**
	 * Metodo para criar desconto inicial
	 * 
	 * @param tipoDeDesconto - desconto a ser setado
	 * @return
	 * @throws ObjetoInexistenteException
	 */
	private BigDecimal criaDesconto(String tipoDeDesconto) throws ObjetoInexistenteException {
		DescontoFactory descontoFactory = new DescontoFactory();
		BigDecimal desconto = descontoFactory.criaDesconto(tipoDeDesconto);
		return desconto;
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
	public BigDecimal getDesconto() {
		return desconto;
	}
        /**
         * Altera o desconto
         * @param desconto desconto a ser alterado
         */
	public void setDesconto(BigDecimal desconto) {
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
