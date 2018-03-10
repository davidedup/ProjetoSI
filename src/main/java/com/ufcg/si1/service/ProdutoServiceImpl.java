package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ufcg.si1.repositories.ProdutosRepository;
import com.ufcg.si1.stategydescontos.BomDesconto;
import com.ufcg.si1.stategydescontos.Desconto;
import com.ufcg.si1.stategydescontos.OtimoDesconto;
import com.ufcg.si1.stategydescontos.SemDesconto;
import com.ufcg.si1.stategydescontos.SuperDesconto;
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

		if (this.produtoExiste(produto)) {
			throw new ObjetoJaExistenteException("O produto " + nome +
					" do fabricante " + fabricante + " ja esta cadastrado!");
		}

		produto = this.produtosRepository.save(produto);
		
		return produto;
	}

	public Produto atualizaProduto(Produto produto, long id) throws ObjetoInexistenteException {
        Produto produtoSalvo = this.produtosRepository.save(produto);
		
        return produtoSalvo;
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

	
	public boolean produtoExiste(Produto produto) {
		long id = produto.getId();
		boolean produtoExiste = this.produtosRepository.exists(id);
		
		return produtoExiste;
	}


	@Override
	public int quantProduto() {
		int quantProdutos =  this.findAllProdutos().size();
		return quantProdutos;
	}


	@Override
	public void atribuiDescontoACategoria(String nomeDaCategoria, String nomeDoDesconto) {
		List<Produto> produtos =  this.findAllProdutos();
		Desconto desconto = this.escolheDescontoPeloNome(nomeDoDesconto);
		
		for (Produto produto : produtos) {
			if(produto.getCategoria().getNome().equals(nomeDaCategoria)) {
				produto.getCategoria().setDesconto(desconto);
				this.produtosRepository.save(produto);
			}
		}

	}
	
	private Desconto escolheDescontoPeloNome(String nomeDoDesconto) {
		Desconto desconto = null;
		
		if(nomeDoDesconto.equalsIgnoreCase("sem desconto")) {
			desconto = new SemDesconto();
		} else if(nomeDoDesconto.equalsIgnoreCase("bom desconto")) {
			desconto = new BomDesconto();
		} else if (nomeDoDesconto.equalsIgnoreCase("otimo desconto")) {
			desconto = new OtimoDesconto();
		}else {
			desconto =  new SuperDesconto();
		}
		
		return desconto;
	}

	@Override
	public List<String> listaCategorias() {
		List<Produto> produtos =  this.findAllProdutos();
		List<String> categoriaNomes = new LinkedList<String>();
		
		for (Produto produto : produtos) {
			String nomeDaCategoria = produto.getCategoria().getNome();
			categoriaNomes.add(nomeDaCategoria);
		}
		
		return categoriaNomes;
	}
	
}
