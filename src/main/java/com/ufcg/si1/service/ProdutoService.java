package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.model.Produto;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;

public interface ProdutoService {

	public List<Produto> findAllProdutos();

	public Produto salvaProduto(Produto produto) throws ObjetoJaExistenteException;

	public Produto findById(long id) throws ObjetoInexistenteException;

	public Produto atualizaProduto(Produto user, long id) throws ObjetoInexistenteException;

	public void deleteProdutoById(long id);

	public Iterator<Produto> getIterator();

	public boolean produtoExiste(Produto produto);

	public int quantProduto();

	public void atribuiDescontoACategoria(String nomeDaCategoria, String nomeDoDesconto);

	public List<String> listaCategorias();
}
