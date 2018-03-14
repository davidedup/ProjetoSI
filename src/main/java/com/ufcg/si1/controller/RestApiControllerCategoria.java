package com.ufcg.si1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.service.ProdutoService;
import com.ufcg.si1.service.ProdutoServiceImpl;
import com.ufcg.si1.util.ObjWrapper;

import exceptions.ObjetoInexistenteException;

@RestController
@CrossOrigin
public class RestApiControllerCategoria {
	
	@Autowired
	private ProdutoService produtoService = new ProdutoServiceImpl();
	
	@RequestMapping(value = "/categoria/{categoria}/desconto/{desconto}", method = RequestMethod.POST)
	public ResponseEntity<?> atribuiDescontoACategoria(@PathVariable("categoria") String nomeDaCategoria, @PathVariable("desconto") String nomeDoDesconto)
			throws ObjetoInexistenteException {
		this.produtoService.atribuiDescontoACategoria(nomeDaCategoria, nomeDoDesconto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lista-categoria", method = RequestMethod.GET)
	public ResponseEntity<?> listaCategorias() {
		List<String> categorias = this.produtoService.listaCategorias();
		ObjWrapper<List<String>> categoriasWrapped = new ObjWrapper<>(categorias);
		return new ResponseEntity<>(categoriasWrapped, HttpStatus.OK);
	}

}
