package com.ufcg.si1.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ufcg.si1.comparator.CategoriaComparator;
import com.ufcg.si1.comparator.FabricanteComparator;
import com.ufcg.si1.comparator.NomeComparator;
import com.ufcg.si1.comparator.PrecoComparator;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Produto;
import com.ufcg.si1.service.ProdutoService;
import com.ufcg.si1.service.ProdutoServiceImpl;
import com.ufcg.si1.util.CustomErrorType;
import com.ufcg.si1.util.ObjWrapper;

@RestController
@RequestMapping("/produto")
@CrossOrigin
public class RestApiControllerProduto {

	@Autowired
	private ProdutoService produtoService = new ProdutoServiceImpl();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.findAllProdutos();

		if (produtos.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Não existe produtos cadastrados"), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/ordenar-nome", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPeloNome() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator<Produto> nomeComparator = new NomeComparator();
		Collections.sort(produtos, nomeComparator);

		return produtos;
	}

	@RequestMapping(value = "/ordenar-preco", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPeloPreco() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator<Produto> precoComparator = new PrecoComparator();
		Collections.sort(produtos, precoComparator);

		return produtos;
	}

	@RequestMapping(value = "/ordenar-categoria", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPelaCategoria() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator<Produto> categoriaComparator = new CategoriaComparator();
		Collections.sort(produtos, categoriaComparator);

		return produtos;
	}

	@RequestMapping(value = "/ordenar-fabricante", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPeloFabricante() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator<Produto> fabricanteComparator = new FabricanteComparator();
		Collections.sort(produtos, fabricanteComparator);

		return produtos;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> criarProduto(@RequestBody Produto produto) throws ObjetoJaExistenteException {
		this.produtoService.salvaProduto(produto);

		return new ResponseEntity<>(produto, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) throws ObjetoInexistenteException {
		Produto produtoRequerido = this.produtoService.findById(id);

		return new ResponseEntity<Produto>(produtoRequerido, HttpStatus.OK);
	}

	@RequestMapping(value = "/atualiza/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizaProduto(@PathVariable("id") long id, @RequestBody Produto produto)
			throws ObjetoInexistenteException {
		try {
			this.produtoService.findById(id);
		} catch (ObjetoInexistenteException e) {
			throw new ObjetoInexistenteException("Não foi possivel atualizar. " + e.getMessage());
		}
		
		this.produtoService.atualizaProduto(produto, id);
		
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Iterable<Produto>> deletaProduto(@PathVariable("id") long id) throws ObjetoInexistenteException {
		try {
			this.produtoService.findById(id);
		} catch (ObjetoInexistenteException e) {
			throw new ObjetoInexistenteException("Não foi possivel deletar o produto. " + e.getMessage());
		}

		Iterable<Produto> produtos = this.produtoService.deleteProdutoById(id);

		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	@RequestMapping(value = "/preco/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultaPreco(@PathVariable("id") long id) throws ObjetoInexistenteException {
		Produto produtoRequerido = this.produtoService.findById(id);
        BigDecimal precoDoProduto = produtoRequerido.precoComDesconto();
        ObjWrapper<?> precoWrapper = new ObjWrapper<>(precoDoProduto);

		return new ResponseEntity<>(precoWrapper, HttpStatus.OK);
	}

	@RequestMapping(value = "/disponibilidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultaDisponibilidade(@PathVariable("id") long id) throws ObjetoInexistenteException {
		Produto produtoRequerido = this.produtoService.findById(id);
		boolean disponibilidade = produtoRequerido.getDisponibilidade();
        	ObjWrapper<?> disponibilidadeWrapper = new ObjWrapper<>(disponibilidade);

		return new ResponseEntity<>(disponibilidadeWrapper, HttpStatus.OK);
	}

	@RequestMapping(value = "/quantidade", method = RequestMethod.GET)
	public ResponseEntity<?> quantProdutos() {
		int quantProdutos = this.produtoService.quantProduto();
		ObjWrapper<?> quantProdutosWrapper = new ObjWrapper<>(quantProdutos);
		
		return new ResponseEntity<>(quantProdutosWrapper, HttpStatus.OK);
	}

}
