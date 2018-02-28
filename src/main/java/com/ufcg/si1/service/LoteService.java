package com.ufcg.si1.service;

import java.util.Iterator;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.DTO.LoteDTO;

public interface LoteService {

	Iterable<Lote> findAllLotes();

	Lote findById(long id);

	void updateProduto(Lote user);

	void deleteLoteById(long id);

	int size();

	Iterator<Lote> getIterator();

	Lote saveLote(Lote lote);

	Lote criarLote(long produtoId, LoteDTO loteDTO);

}
