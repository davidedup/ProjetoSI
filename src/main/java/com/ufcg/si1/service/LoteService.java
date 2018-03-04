package com.ufcg.si1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.model.ItemVenda;
import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.VendaItem;
import com.ufcg.si1.model.DTO.LoteDTO;

public interface LoteService {

	public Iterable<Lote> findAllLotes();

	public Lote findById(long id);

	public void updateProduto(Lote user);

	public void deleteLoteById(long id);

	public int size();

	public Iterator<Lote> getIterator();

	public Lote saveLote(Lote lote);

	public Lote criarLote(long produtoId, LoteDTO loteDTO);

	public void atualizaQuantProduto(List<VendaItem> produtosVendidos);

	public int quantProduto(long produtoId);

	public int quantLotes();

	public List<String> getValidades();



}
