package com.ufcg.si1.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "tb_lote")
public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Autowired
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@Column(name = "numero_de_itens")
	private int numeroDeItens;

	@Column(name = "data_de_validade")
	private String dataDeValidade;

	public Lote() {
		this.id = 0;
	}

	public Lote(long id, Produto produto, int numeroDeItens, String dataDeValidade) {
		this.id = id;
		this.produto = produto;
		this.numeroDeItens = numeroDeItens;
		this.dataDeValidade = dataDeValidade;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getNumeroDeItens() {
		return this.numeroDeItens;
	}

	public void setNumeroDeItens(int numeroDeItens) {
		this.numeroDeItens = numeroDeItens;
	}

	public String getDataDeValidade() {
		return this.dataDeValidade;
	}

	public void setDataDeValidade(String dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

	@Override
	public String toString() {
		return "Lote{" + "id=" + id + ", produto=" + produto.getId() + ", numeroDeItens=" + numeroDeItens
				+ ", dataDeValidade='" + dataDeValidade + '\'' + '}';
	}

	public boolean pertoDeVencer() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataDeVencimento = null;
		Date date = new Date();

		try {
			dataDeVencimento = formato.parse(this.dataDeValidade);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long dias = 24L * 60L * 60L * 1000L;
		long diferenca = ((dataDeVencimento.getTime() - date.getTime() ) / dias);
		
		if (diferenca <= 30) {
			
			return true;
		}
		return false;

	}

	public boolean estaNaValidade() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataDeVencimento = null;
		Date date = new Date();
		try {
			dataDeVencimento = formato.parse(this.dataDeValidade);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long dias = 24L * 60L * 60L * 1000L;
		long diferenca = ((dataDeVencimento.getTime() - date.getTime() ) / dias);
		
		if (diferenca <= 0) {
			return true;
		}
		return false;
		
	}

}
