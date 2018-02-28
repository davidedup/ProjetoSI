package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.model.Produto;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;

public interface ProdutoService {

	List<Produto> findAllProdutos();

	Produto salvaProduto(Produto produto) throws ObjetoJaExistenteException;

	Produto findById(long id) throws ObjetoInexistenteException;

	Produto atualizaProduto(Produto user) throws ObjetoInexistenteException;

	void deleteProdutoById(long id);

	Iterator<Produto> getIterator();

	boolean doesProdutoExist(Produto produto);

}
