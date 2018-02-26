package com.ufcg.si1.controller;

import java.util.List;

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
	
	LoteService loteService = new LoteServiceImpl();
	
	 
	
	@RequestMapping(value = "/lote/", method = RequestMethod.GET)
	public ResponseEntity<List<Lote>> listAllLotess() {
		List<Lote> lotes = (List<Lote>) loteService.findAllLotes();

		if (lotes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
	}

	
//	@RequestMapping(value = "/produto/{id}/lote", method = RequestMethod.POST)
//	public ResponseEntity<?> criarLote(@PathVariable("id") long produtoId, @RequestBody LoteDTO loteDTO) {
//		Produto product = produtoService.findById(produtoId);
//
//		if (product == null) {
//			return new ResponseEntity(
//					new CustomErrorType("Unable to create lote. Produto with id " + produtoId + " not found."),
//					HttpStatus.NOT_FOUND);
//		}
//
//		Lote lote = loteService.saveLote(new Lote(product, loteDTO.getNumeroDeItens(), loteDTO.getDataDeValidade()));
//
//		if (!product.getDisponibilidade()) {
//			if (loteDTO.getNumeroDeItens() > 0) {
//				Produto produtoDisponivel = product;
//				produtoDisponivel.mudaDisponibilidade();
//				produtoService.updateProduto(produtoDisponivel);
//			}
//		}
//
//		return new ResponseEntity<>(lote, HttpStatus.CREATED);
//	}
	
}