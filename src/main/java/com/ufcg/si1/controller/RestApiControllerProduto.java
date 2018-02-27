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
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.DTO.LoteDTO;
import com.ufcg.si1.service.ProdutoService;
import com.ufcg.si1.service.ProdutoServiceImpl;
import com.ufcg.si1.util.CustomErrorType;

@RestController
@RequestMapping("/produto")
@CrossOrigin
public class RestApiControllerProduto {

	@Autowired
	ProdutoService produtoService = new ProdutoServiceImpl();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> litarProdutos() {
		List<Produto> produtos =  produtoService.findAllProdutos();

		if (produtos.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Não existe produtos cadastrados"), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}


	@RequestMapping(value = "/ordenar-nome", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPeloNome() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator nomeComparator = new NomeComparator();
		Collections.sort(produtos, nomeComparator);

		return produtos;
	}


	@RequestMapping(value = "/ordenar-preco", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPeloPreco() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator precoComparator = new PrecoComparator();
		Collections.sort(produtos, precoComparator);

		return produtos;
	}


	@RequestMapping(value = "/ordenar-categoria", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPelaCategoria() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator categoriaComparator = new CategoriaComparator();
		Collections.sort(produtos, categoriaComparator);

		return produtos;
	}

	@RequestMapping(value = "/ordenar-fabricante", method = RequestMethod.GET)
	public List<Produto> listarProdutosOrdenadosPeloFabricante() {
		List<Produto> produtos = this.produtoService.findAllProdutos();
		Comparator fabricanteComparator = new FabricanteComparator();
		Collections.sort(produtos, fabricanteComparator);

		return produtos;
	}



	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> criarProduto(@RequestBody Produto produto, UriComponentsBuilder ucBuilder)
			throws ObjetoJaExistenteException {

		Produto produtoSalvo = this.produtoService.salvaProduto(produto);
		return new ResponseEntity<>(produto, HttpStatus.CREATED);

//		produtoService.criaProduto(produto);
//		
//		boolean produtoExiste = false;
//
//		for (Produto p : produtoService.findAllProdutos()) {
//			if (p.getCodigoBarra().equals(produto.getCodigoBarra())) {
//				produtoExiste = true;
//			}
//		}
//
//		if (produtoExiste) {
//			return new ResponseEntity(new CustomErrorType("O produto " + produto.getNome() + " do fabricante "
//					+ produto.getFabricante() + " ja esta cadastrado!"), HttpStatus.CONFLICT);
//		}
//
//		produto.setDisponibilidade(false);
//
//		produtoService.saveProduto(produto);

		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/api/produto/{id}").buildAndExpand(produto.getId()).toUri());		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) throws ObjetoInexistenteException {
		Produto produtoRequerido = this.produtoService.findById(id);

			return new ResponseEntity<Produto>(produtoRequerido, HttpStatus.OK); }

		
//		Produto p = null;
//
//		for (Produto produto : produtoService.findAllProdutos()) {
//			if (produto.getId() == id) {
//				p = produto;
//			}
//		}
//
//		if (p == null) {
//			return new ResponseEntity(new CustomErrorType("Produto with id " + id + " not found"),
//					HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Produto>(p, HttpStatus.OK);


	@RequestMapping(value = "/atualiza/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> atualizaProduto(@PathVariable("id") long id, @RequestBody Produto produto)
			throws ObjetoInexistenteException {
		try {
			Produto produtoRequerido = this.produtoService.findById(id);
		} catch (ObjetoInexistenteException e) {
			throw new ObjetoInexistenteException("Não foi possivel atualizar. " + e.getMessage());
		}

		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
		
//		Produto currentProduto = null;
//
//		for (Produto p : produtoService.findAllProdutos()) {
//			if (p.getId() == id) {
//				currentProduto = p;
//			}
//		}
//
//		if (currentProduto == null) {
//			return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with id " + id + " not found."),
//					HttpStatus.NOT_FOUND);
//		}
//
//		currentProduto.mudaNome(produto.getNome());
//		currentProduto.setPreco(produto.getPreco());
//		currentProduto.setCodigoBarra(produto.getCodigoBarra());
//		currentProduto.mudaFabricante(produto.getFabricante());
//		currentProduto.mudaCategoria(produto.getCategoria());

		// resolvi criar um servi�o na API s� para mudar a situa��o do produto
		// esse c�digo n�o precisa mais
		// try {
		// currentProduto.mudaSituacao(produto.pegaSituacao());
		// } catch (ObjetoInvalidoException e) {
		// return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with
		// id " + id + " invalid."),
		// HttpStatus.NOT_FOUND);
		// }

		//produtoService.updateProduto(currentProduto);
		//return new ResponseEntity<Produto>(currentProduto, HttpStatus.OK);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletaProduto(@PathVariable("id") long id) throws ObjetoInexistenteException {
		try {
			Produto produtoParaDeletar = this.produtoService.findById(id);
		} catch (ObjetoInexistenteException e) {
			throw new ObjetoInexistenteException("Não foi possivel deletar o produto. " + e.getMessage());
		}

		this.produtoService.deleteProdutoById(id);
		return new ResponseEntity<Produto>(HttpStatus.OK);

//		Produto user = null;
//
//		for (Produto produto : produtoService.findAllProdutos()) {
//			if (produto.getId() == id) {
//				user = produto;
//			}
//		}
//
//		if (user == null) {
//			return new ResponseEntity(new CustomErrorType("Unable to delete. Produto with id " + id + " not found."),
//					HttpStatus.NOT_FOUND);
//		}
//		produtoService.deleteProdutoById(id);
//		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/preco/{id}", method = RequestMethod.GET)
	public ResponseEntity<BigDecimal> consultaPreco(@PathVariable("id") long id) throws ObjetoInexistenteException {
		Produto produtoRequerido = this.produtoService.findById(id);

		BigDecimal precoDoProduto = produtoRequerido.getPreco();
		return new ResponseEntity<BigDecimal>(precoDoProduto, HttpStatus.OK);

//		BigDecimal precoDoProduto = null;
//
//		for (Produto produto : produtoService.findAllProdutos()) {
//			if (produto.getId() == id) {
//				precoDoProduto = produto.getPreco();
//			}
//		}
//
//		if (precoDoProduto == null) {
//			return new ResponseEntity(new CustomErrorType("Produto with id " + id + " not found"),
//					HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity<BigDecimal>(precoDoProduto, HttpStatus.OK);
	}

	@RequestMapping(value = "/disponibilidade/{id}", method = RequestMethod.GET)
	public boolean consultaDisponibilidade(@PathVariable("id") long id) throws ObjetoInexistenteException {
		Produto produtoRequerido = this.produtoService.findById(id);
		boolean disponibilidade = produtoRequerido.getDisponibilidade();

		return disponibilidade;
//		Boolean disponivel = false;
//
//		for (Produto produto : produtoService.findAllProdutos()) {
//			if (produto.getId() == id) {
//				disponivel = produto.getDisponibilidade();
//			}
//		}
//
//		return disponivel;
//	}
	}

}
