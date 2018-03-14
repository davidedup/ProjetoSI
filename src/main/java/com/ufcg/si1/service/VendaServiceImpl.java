package com.ufcg.si1.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Venda;
import com.ufcg.si1.model.VendaItem;
import com.ufcg.si1.repositories.VendasRepository;
import com.ufcg.si1.util.Util;

import java.util.List;

@Service("vendaService")
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendasRepository vendasRepository;
	@Autowired
	private LoteService loteService;

	@Override
	public Venda cadastraVenda(List<VendaItem> produtosVendidos, String dataDaVenda) {
		System.out.println("Venda service impl " + produtosVendidos.size());
		Venda vendaParaSalva = new Venda(produtosVendidos, dataDaVenda);
		System.out.println("Venda Impl: criou venda");
		System.out.println("Venda Impl: criou venda " + vendaParaSalva.getId());
		this.loteService.atualizaQuantProduto(produtosVendidos);
		System.out.println("Venda Impl: voltou da criação de venda");
		
		for (VendaItem produto: vendaParaSalva.getProdutosVendidos()) {
			System.out.println("Venda impl: " + produto.getQuantidade());
			System.out.println("Venda impl: " + produto.getId());
			System.out.println("Venda impl: " + produto.getProduto().getId());
			System.out.println("Venda impl: " + produto.getProduto().getId());
		}
		
		this.loteService.atualizaDisponibilidadeDeProdutos();
		
		return vendasRepository.save(vendaParaSalva);
	}

	@Override
	public List<Venda> findAllVendas() {
		Iterable<Venda> vendas = vendasRepository.findAll();
		List<Venda> vendasLista = Util.toList(vendas);
		return vendasLista;
	}

	@Override
	public BigDecimal calculaTotalDeVendas() {
		BigDecimal totalDasVendas = new BigDecimal(0.0);
		List<Venda> vendas = this.findAllVendas();

		for (Venda venda : vendas) {
			totalDasVendas = venda.calculaTotal().add(totalDasVendas);
		}
		
		return totalDasVendas;
	}

	@Override
	public Venda cancelaVenda(long id) {
		Venda venda = this.vendasRepository.findOne(id);
		this.loteService.incrementaQuantProdutos(venda.getProdutosVendidos());
		this.vendasRepository.delete(id);
		return venda;
		
	}

}
