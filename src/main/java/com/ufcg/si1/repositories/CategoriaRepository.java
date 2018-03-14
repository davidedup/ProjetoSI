package com.ufcg.si1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ufcg.si1.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
	
	public Categoria getCategoriaByNome(String nome);
	
}
