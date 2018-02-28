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
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Lote>> listarLotes() {
		List<Lote> lotes = (List<Lote>) loteService.findAllLotes();

		if (lotes.isEmpty()) {
			return new ResponseEntity<List<Lote>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/produto/{id}/lote", method = RequestMethod.POST)
	public ResponseEntity<?> criarLote(@PathVariable("id") long produtoId, @RequestBody LoteDTO loteDTO) {
		Lote lote = this.loteService.criarLote(produtoId, loteDTO);
		
		if (lote == null) {
			return new ResponseEntity<>(new CustomErrorType("Não foi possivel criar. Produto com id: " + produtoId + " não encontrado."), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(lote, HttpStatus.CREATED);
	}

}