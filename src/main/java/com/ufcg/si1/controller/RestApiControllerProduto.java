package com.ufcg.si1.controller;

import java.math.BigDecimal;
import java.util.List;

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
	
	ProdutoService produtoService = new ProdutoServiceImpl();
	
	@RequestMapping(value = "/produto/", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listAllUsers() {
		List<Produto> produtos =  produtoService.findAllProdutos();

		if (produtos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}


	@RequestMapping(value = "/produto/", method = RequestMethod.POST)
	public ResponseEntity<?> criarProduto(@RequestBody Produto produto, UriComponentsBuilder ucBuilder) {
		Produto produtoSalvo = this.produtoService.criaProduto(produto);
		
		if (produto == null) {
			String mensagem = "O produto " + produtoSalvo.getNome() + " do fabricante "	+ produtoSalvo.getFabricante() + " ja esta cadastrado!";
			return new ResponseEntity(new CustomErrorType(mensagem), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
		}
		
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

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) {
		Produto produtoRequerido = this.produtoService.findById(id);
		
		if(produtoRequerido == null) {
			//TODO:  mudar idioma
			return new ResponseEntity(new CustomErrorType("Produto with id " + id + " not found"),	HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<Produto>(produtoRequerido, HttpStatus.OK);
		}
		
		
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
	}

	
	//TODO: mudar noome do emtodo para portugues
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduto(@PathVariable("id") long id, @RequestBody Produto produto) {
		Produto produtoRequerido = this.produtoService.findById(id);
		
		if(produtoRequerido == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Produto with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}else {
			produto = this.produtoService.atualizaProduto(produto); 
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
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		Produto produtoParaDeletar = this.produtoService.findById(id);
		
		if(produtoParaDeletar == null) {
			return new ResponseEntity(new CustomErrorType("Unable to delete. Produto with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}else {
			this.produtoService.deleteProdutoById(id);
			return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
			
		}
		
		
		
		
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

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<BigDecimal> consultaPreco(@PathVariable("id") long id) {
		Produto produtoRequerido = this.produtoService.findById(id); 
		
		if(produtoRequerido == null) {
			return new ResponseEntity(new CustomErrorType("Produto with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}else {
			BigDecimal precoDoProduto = produtoRequerido.getPreco();
			return new ResponseEntity<BigDecimal>(precoDoProduto, HttpStatus.OK);
		}
		
		
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

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public boolean consultaDisponibilidade(@PathVariable("id") long id) {
		Produto produtoRequerido = this.produtoService.findById(id); 
		
		if(produtoRequerido == null) {
			return false;
		}else {
			return true; 
		}
		
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