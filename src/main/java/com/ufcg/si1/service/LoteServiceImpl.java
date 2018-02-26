package com.ufcg.si1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.repositories.LotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loteService")
public class LoteServiceImpl implements LoteService {

//	private static final AtomicLong counter = new AtomicLong();
//
//	private static List<Lote> lotes;

	@Autowired
	private LotesRepository lotesRepository;

//	static {
//		lotes = new ArrayList<>();
//	}

	@Override
	public Lote saveLote(Lote lote) {
		Lote savedLote = lotesRepository.save(lote);

//		lote.setId(counter.incrementAndGet());
//		lotes.add(lote);

		return savedLote;
	}

	@Override
	public Lote findById(long id) {
		Lote lote = this.lotesRepository.getLoteById(id);
		return lote;
//		Iterable<Lote> lotes = this.lotesRepository.findAll();
//
//		for (Lote lote : lotes) {
//			if (lote.getId() == id) {
//				return lote;
//			}
//		}
//		return null;
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

//		int index = lotes.indexOf(lote);
//		lotes.set(index, lote);
	}

	@Override
	public void deleteLoteById(long id) {
		Lote lote = this.findById(id);
		this.lotesRepository.delete(lote);

//		for (Iterator<Lote> iterator = lotes.iterator(); iterator.hasNext();) {
//			Lote lote = iterator.next();
//			if (lote.getId() == id) {
//				iterator.remove();
//			}
//		}
	}

	@Override
	public Iterable<Lote> findAllLotes() {
		Iterable<Lote> lotes = this.lotesRepository.findAll();
		return lotes;
	}

	@Override
	public int size() {
		Iterator iterator = this.getIterator();
		int size = 0;

		while(iterator.hasNext()) {
			iterator.next();
			size++;
		}

		return size;
	}

	@Override
	public Iterator<Lote> getIterator() {
		Iterable<Lote> lotes = this.findAllLotes();
		Iterator iterator = lotes.iterator();
		return iterator;
	}
}
