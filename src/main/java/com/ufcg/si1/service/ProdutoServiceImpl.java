package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.repositories.ProdutosRepository;
import com.ufcg.si1.util.Util;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	public Produto salvaProduto(Produto produto) throws ObjetoJaExistenteException {
		String nome = produto.getNome();
		String fabricante = produto.getFabricante();

		if (this.doesProdutoExist(produto)) {
			throw new ObjetoJaExistenteException("O produto " + nome +
					" do fabricante " + fabricante + " ja esta cadastrado!");
		}

		produto = this.produtosRepository.save(produto);
		
		return produto;
	}

	public Produto atualizaProduto(Produto produto) throws ObjetoInexistenteException {
        produto = this.produtosRepository.save(produto);
        
        return produto;
	}

	public void deleteProdutoById(long id) {
		this.produtosRepository.delete(id);
	}

	public Iterator<Produto> getIterator() {
		Iterable<Produto> produtos = this.produtosRepository.findAll();
		Iterator<Produto> iterator = produtos.iterator();

		return iterator;
	}

	public void deleteAllUsers() {
		this.produtosRepository.deleteAll();
	}

	public Produto findById(long id) throws ObjetoInexistenteException {
		Produto produto = this.produtosRepository.getProdutoById(id);

		if (produto == null) {
			throw new ObjetoInexistenteException("O produto com o id: " + id + " não foi encontrado");
		}

		return produto;
	}

	
	//TODO: mudar para portugues
	public boolean doesProdutoExist(Produto produto) {
		long id = produto.getId();
		boolean produtoExiste = this.produtosRepository.exists(id);
		
		return produtoExiste;
	}


	@Override
	public int quantProduto() {
		int quantProdutos =  this.findAllProdutos().size();
		return quantProdutos;
	}
	
	
	
}
