package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.DTO.LoteDTO;
import com.ufcg.si1.service.LoteService;
import com.ufcg.si1.service.LoteServiceImpl;
import com.ufcg.si1.util.CustomErrorType;

@RestController
@RequestMapping("/lote")
@CrossOrigin
public class RestApiControllerLote {
	
	@Autowired
	LoteService loteService = new LoteServiceImpl();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Lote>> listarLotes() {
		List<Lote> lotes = (List<Lote>) loteService.findAllLotes();

		if (lotes.isEmpty() || lotes == null) {
			return new ResponseEntity<List<Lote>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
	}

	@RequestMapping(value = "/produto/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> criarLote(@PathVariable("id") long produtoId, @RequestBody LoteDTO loteDTO) {
		Lote lote = this.loteService.criarLote(produtoId, loteDTO);
		
		if (lote == null) {
			return new ResponseEntity<>(new CustomErrorType("Não foi possivel criar. Produto com id: " + produtoId + " não encontrado."), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(lote, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/quantidade/produto/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> quantidadeProduto(@PathVariable("id") long produtoId) {
		int quantidade = this.loteService.quantProduto(produtoId);
		
		if(quantidade == 0) {
			return new ResponseEntity<>(new CustomErrorType("Produto não encontrado"), HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<>(quantidade, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/quantidade", method = RequestMethod.GET)
	public ResponseEntity<?> quantidadeLotes() {
        int quantidade = this.loteService.quantLotes();
        
		return new ResponseEntity<>(quantidade, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validades", method = RequestMethod.GET)
	public ResponseEntity<?> validades()  {
		List<String> validades = this.loteService.getValidades();
		return new ResponseEntity<>(validades, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lista-produtos-baixa-quant", method = RequestMethod.GET)
	public ResponseEntity<?> listaProdutosBaixaQaunt() {
		List<Produto> produtosPoucaQuant = this.loteService.listaProdutosBaixaQaunt();
		return new ResponseEntity<>(produtosPoucaQuant, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lista-perto-vencer", method = RequestMethod.GET)
	public ResponseEntity<?> listaProximoDeVencer() {
		List<Produto> produtosProximoDaValidade = this.loteService.listaProximoDeVencer();
		return new ResponseEntity<>(produtosProximoDaValidade , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lista-produtos-disponiveis", method = RequestMethod.GET)
	public ResponseEntity<?> listaDisponiveis() {
		List<Produto> produtosDisponiveis = this.loteService.listaDisponiveis();
		return new ResponseEntity<>(produtosDisponiveis, HttpStatus.OK);
	}
	
}