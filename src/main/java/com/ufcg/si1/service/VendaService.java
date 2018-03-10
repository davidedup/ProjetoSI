package com.ufcg.si1.service;

import java.math.BigDecimal;
import java.util.List;

import com.ufcg.si1.model.Venda;
import com.ufcg.si1.model.VendaItem;

public interface VendaService {
	
	public Venda cadastraVenda(List<VendaItem> produtosVendidos, String dataDaVenda);
	
	public BigDecimal calculaTotalDeVendas();

	public Venda cancelaVenda(long id);

	List<Venda> findAllVendas();

}
