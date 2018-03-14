package com.ufcg.si1.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "tb_venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Autowired
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "tb_produtos_vendidos")
	private List<VendaItem> produtosVendidos;

	@Column(name = "data_da_venda")
	private String dataDaVenda;

	public Venda() {
		this.produtosVendidos = new ArrayList<>();
	}

	public Venda(List<VendaItem> produtosVendidos, String dataDaVenda) {
		this.produtosVendidos = new ArrayList<>();
		
		System.out.println("Venda: " + dataDaVenda);
		System.out.println("Venda: " + produtosVendidos);
		
		for (VendaItem vendaItem: produtosVendidos) {
			System.out.println("Venda: " + vendaItem);
			System.out.println("Venda: " + vendaItem.getQuantidade());
			System.out.println("Venda: " + vendaItem.getProduto());
			System.out.println("Venda: " + vendaItem.getProduto().getNome());
			System.out.println("Venda: " + vendaItem.getProduto().getId());

			this.produtosVendidos.add(vendaItem);
			
			System.out.println("Venda: salvou");
		}
		this.dataDaVenda = dataDaVenda;	
	}

	public BigDecimal calculaTotal() {
		BigDecimal totalDaVenda = new BigDecimal(0.0);

		for (VendaItem vendaItem : this.produtosVendidos) {
			Produto produto = vendaItem.getProduto();
			int quantidadeDeProdutos = vendaItem.getQuantidade();
			BigDecimal precoComDesconto = produto.precoComDesconto();
			BigDecimal intParaBigDecimal = new BigDecimal(quantidadeDeProdutos);
			totalDaVenda = precoComDesconto.multiply(intParaBigDecimal).add(totalDaVenda);
		}

		return totalDaVenda;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<VendaItem> getProdutosVendidos() {
		return produtosVendidos;
	}

	public void setProdutosVendidos(List<VendaItem> produtosVendidos) {
		this.produtosVendidos = produtosVendidos;
	}

	public String getDataDaVenda() {
		return dataDaVenda;
	}

	public void setDataDaVenda(String dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}

}
