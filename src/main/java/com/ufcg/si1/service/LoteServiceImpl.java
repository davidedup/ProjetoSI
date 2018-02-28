package com.ufcg.si1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.DTO.LoteDTO;
import com.ufcg.si1.repositories.LotesRepository;
import com.ufcg.si1.repositories.ProdutosRepository;
import com.ufcg.si1.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loteService")
public class LoteServiceImpl implements LoteService {

	@Autowired
	private LotesRepository lotesRepository;
	private ProdutosRepository produtoRepository;

	@Override
	public Lote saveLote(Lote lote) {
		Lote savedLote = lotesRepository.save(lote);

		return savedLote;
	}

	@Override
	public Lote findById(long id) {
		Lote lote = this.lotesRepository.getLoteById(id);

		return lote;
	}

	/**
	 * Faz o update do lote, lote é recebido como parâmetro
	 * já com o novo produto como atributo
	 *
	 * @param lote lote a ser atualizado
	 */
	@Override
	public void updateProduto(Lote lote) {
		this.lotesRepository.save(lote);
	}

	@Override
	public void deleteLoteById(long id) {
		Lote lote = this.findById(id);
		this.lotesRepository.delete(lote);
	}

	@Override
	public List<Lote> findAllLotes() {
		Iterable<Lote> lotes = this.lotesRepository.findAll();
		List<Lote> lotesList = Util.toList(lotes);
		
		return lotesList;
	}

	@Override
	public int size() {
		List<Lote> lotes = this.findAllLotes();
		int size = lotes.size();
		
		return size;
	}

	@Override
	public Iterator<Lote> getIterator() {
		Iterable<Lote> lotes = this.findAllLotes();
		Iterator<Lote> iterator = lotes.iterator();
		
		return iterator;
	}

	@Override
	public Lote criarLote(long produtoId, LoteDTO loteDTO) {
		Lote loteParaSalvar = null;
		Produto produto = this.produtoRepository.findOne(produtoId);
		
		if(produto != null){
			int numeroDeItens = loteDTO.getNumeroDeItens();
			String validade = loteDTO.getDataDeValidade();
			loteParaSalvar = new Lote(produtoId, produto, numeroDeItens, validade);
			lotesRepository.save(loteParaSalvar);
		}
		
		return loteParaSalvar;
	}

}
