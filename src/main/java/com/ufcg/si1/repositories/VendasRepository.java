package com.ufcg.si1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ufcg.si1.model.Venda;

public interface VendasRepository extends CrudRepository<Venda, Long> {

	public Venda getLoteById(Long id);

}
