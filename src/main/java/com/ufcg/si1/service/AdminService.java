package com.ufcg.si1.service;

import com.ufcg.si1.model.Admin;
import com.ufcg.si1.util.Token;

import exceptions.ObjetoInvalidoException;
import exceptions.ObjetoJaExistenteException;

public interface AdminService {
	
	void cadastrarAdmin(Admin admin) throws ObjetoJaExistenteException;
	
	Token autenticarAdmin(Admin admin) throws ObjetoInvalidoException;

	String pegarNomeAdmin(String autorizacao);
	
}
