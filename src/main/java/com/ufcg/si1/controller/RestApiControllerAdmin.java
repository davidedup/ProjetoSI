package com.ufcg.si1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufcg.si1.model.Admin;
import com.ufcg.si1.service.AdminServiceImpl;
import com.ufcg.si1.util.Token;

import exceptions.ObjetoInvalidoException;
import exceptions.ObjetoJaExistenteException;

@RestController
@CrossOrigin
public class RestApiControllerAdmin {
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public Token autenticar(@RequestBody Admin admin) throws ObjetoInvalidoException {
		Token token = this.adminServiceImpl.autenticarAdmin(admin);
		
		return token;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public void cadastrar(@RequestBody Admin admin) throws ObjetoJaExistenteException {
		this.adminServiceImpl.cadastrarAdmin(admin);
	}

}
