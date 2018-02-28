package com.ufcg.si1.repositories;

import com.ufcg.si1.model.Lote;
import org.springframework.data.repository.CrudRepository;

public interface LotesRepository extends CrudRepository<Lote, Long> {

	public Lote getLoteById(Long id);

}
