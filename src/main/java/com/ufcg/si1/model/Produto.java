package com.ufcg.si1.model;

import java.math.BigDecimal;

import exceptions.ObjetoInvalidoException;

import javax.persistence.*;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private long id;

    @Column(name = "nome")
	private String nome;

    @Column(name = "preco")
	private BigDecimal preco;

    @Column(name = "codigo_barra")
	private String codigoBarra;

    @Column(name = "fabricante")
	private String fabricante;

    @Column(name = "categoria")
	private String categoria;

    @Column(name = "situacao")
	public int situacao; // usa variaveis estaticas abaixo
	/* situacoes do produto */
	public static final int DISPONIVEL = 1;
	public static final int INDISPONIVEL = 2;

	public Produto() {
		this.id = 0;
		this.preco = new BigDecimal(0);
	}

	public Produto(long id, String nome, String codigoBarra, String fabricante,
			String nomeCategoria) {
		this.id = id;
		this.nome = nome;
		this.preco = new BigDecimal(0);
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.categoria = nomeCategoria;
		this.situacao = Produto.INDISPONIVEL;
	}

	public String getNome() {
		return nome;
	}

	public void mudaNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public long getId() {
		return id;
	}

	public void mudaId(long codigo) {
		this.id = codigo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void mudaFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void mudaCategoria(String categoria) {
		this.categoria = categoria;
	}
		
	public void mudaSituacao(int situacao) throws ObjetoInvalidoException {
		switch (situacao) {
		case 1:
			this.situacao = Produto.DISPONIVEL;
			break;
		case 2:
			this.situacao = Produto.INDISPONIVEL;
			break;

		default:
			throw new ObjetoInvalidoException("Situacao Invalida");
		}
	}

	public int getSituacao() throws ObjetoInvalidoException {
		return this.situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
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
		Produto other = (Produto) obj;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}