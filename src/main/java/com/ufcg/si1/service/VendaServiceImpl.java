package com.ufcg.si1.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.Venda;
import com.ufcg.si1.model.VendaItem;
import com.ufcg.si1.repositories.ProdutosRepository;
import com.ufcg.si1.repositories.VendasRepository;
import com.ufcg.si1.util.Util;
import java.util.List;

public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendasRepository vendasRepository;
	private LoteService loteService;

	@Override
	public Venda cadastraVenda(List<VendaItem> produtosVendidos, String dataDaVenda) {
		Venda vendaParaSalva = new Venda(produtosVendidos, dataDaVenda);
		
		loteService.atualizaQuantProduto(produtosVendidos);
		
		return vendasRepository.save(vendaParaSalva);
		
	}

	@Override
	public List<Venda> findAllLotes() {
		Iterable<Venda> vendas = vendasRepository.findAll();
		List<Venda> vendasLista = Util.toList(vendas);
		return vendasLista;
	}

	@Override
	public BigDecimal calculaTotalDeVendas() {
		BigDecimal totalDasVendas = new BigDecimal(0.0);
		List<Venda> vendas = this.findAllLotes();

		for (Venda venda : vendas) {
			totalDasVendas = venda.calculaTotal().add(totalDasVendas);
		}
		
		return totalDasVendas;
	}

}
