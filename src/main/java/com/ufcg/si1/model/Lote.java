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
	
	public boolean estaVencido() {
		boolean estaVencido = this.diferencaDeDataMenorIgualQue(-1);
		return estaVencido;
	}
	
	public boolean pertoDeVencer() {
		boolean estaPertoDeVencer = this.diferencaDeDataMenorIgualQue(30);
		return estaPertoDeVencer;
	}
	
	private boolean diferencaDeDataMenorIgualQue(int quantidadeDeDias) {
		Date dataDeVencimento = dataLoteDate();
		
		Date date = new Date();
		long diferenca = diferencaDias(dataDeVencimento, date);
		
		if (diferenca <= quantidadeDeDias) {	
			return true;
		}
		return false;
	}

	private Date dataLoteDate() {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Date dataDeVencimento = null;
		
		try {
			dataDeVencimento = formato.parse(this.dataDeValidade);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataDeVencimento;
	}

	private long diferencaDias(Date dataDeVencimento, Date date) {
		long dias = 24L * 60L * 60L * 1000L;
		long diferenca = ((dataDeVencimento.getTime() - date.getTime() ) / dias);
		return diferenca;
	}
	

	@Override
	public String toString() {
		return "Lote{" + "id=" + id + ", produto=" + produto.getId() + ", numeroDeItens=" + numeroDeItens
				+ ", dataDeValidade='" + dataDeValidade + '\'' + '}';
	}
}
