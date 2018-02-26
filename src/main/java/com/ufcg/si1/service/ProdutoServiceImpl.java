package com.ufcg.si1.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.ufcg.si1.repositories.ProdutosRepository;
import com.ufcg.si1.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;

@Service("produtoService")
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutosRepository produtosRepository;

	public List<Produto> findAllProdutos() {
		Iterable<Produto> produtos = this.produtosRepository.findAll();
		List<Produto> produtosList = Util.toList(produtos);
		return produtosList;
	}


	public void saveProduto(Produto produto) {
		this.produtosRepository.save(produto);
	}

	public void updateProduto(Produto produto) {
		this.produtosRepository.save(produto);
	}

	public void deleteProdutoById(long id) {
		this.produtosRepository.delete(id);
	}

	public Iterator<Produto> getIterator() {
		Iterable<Produto> produtos = this.produtosRepository.findAll();
		Iterator iterator = produtos.iterator();

		return iterator;
	}

	public void deleteAllUsers() {
		this.produtosRepository.deleteAll();
	}

	public Produto findById(long id) {
		Produto produto = this.produtosRepository.getProdutoById(id);
		return produto;
	}

	public boolean doesProdutoExist(Produto produto) {
		long id = produto.getId();
		boolean produtoExiste = this.produtosRepository.exists(id);
		return produtoExiste;
	}
}
