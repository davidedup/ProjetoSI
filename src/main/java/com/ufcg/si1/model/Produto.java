package com.ufcg.si1.model;

import java.math.BigDecimal;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import exceptions.ObjetoInexistenteException;


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

	@Column(name = "situacao")
	private boolean disponivel;

	@Autowired
    @ManyToOne
    @JoinColumn(name = "id_produto")
	private Categoria categoria;
	
	public Produto() throws ObjetoInexistenteException {
		this.id = 0;
		this.preco = new BigDecimal(0);
		this.categoria = new Categoria("");
	}

	public Produto(String nome, String codigoBarra, String fabricante, String nomeCategoria) throws ObjetoInexistenteException {
		this.nome = nome;
		this.preco = new BigDecimal(0);
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.disponivel = false;
		this.categoria = new Categoria(nomeCategoria);
	}

	public String getNome() {
		return this.nome;
	}

	public void mudaNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public long getId() {
		return this.id;
	}
	
	public void mudaId(long codigo) {
		this.id = codigo;
	}

	public String getFabricante() {
		return this.fabricante;
	}

	public void mudaFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getCodigoBarra() {
		return this.codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public boolean getDisponibilidade() {
		return this.disponivel;
	}
	
	public void setDisponibilidade(boolean disponivel) {
		this.disponivel = disponivel;
	}

	 public BigDecimal precoComDesconto() {
		 BigDecimal desconto = this.categoria.getDesconto();
		 BigDecimal precoComDesconto = this.preco.multiply(desconto);
		 return precoComDesconto;
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