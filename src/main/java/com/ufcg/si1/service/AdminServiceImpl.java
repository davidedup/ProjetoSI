package com.ufcg.si1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.si1.model.Admin;
import com.ufcg.si1.repositories.AdminRepository;
import com.ufcg.si1.util.Token;

import exceptions.ObjetoInvalidoException;
import exceptions.ObjetoJaExistenteException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service("adminService")
public class AdminServiceImpl {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public void cadastrarAdmin(Admin admin) throws ObjetoJaExistenteException {
		String login = admin.getLogin();

        if (this.adminRepository.exists(login)) {
            throw new ObjetoJaExistenteException("Login ja existente, tente outro");
        }

        admin = this.adminRepository.save(admin);
	}
	
	public Token autenticarAdmin(Admin admin) throws ObjetoInvalidoException {
		String login = admin.getLogin();
        String senha = admin.getSenha();

        if (!this.adminRepository.exists(login) ||
                !this.adminRepository.getUsuarioByLogin(login).getSenha().equals(senha)) {
            throw new ObjetoInvalidoException("Admin ou senha invalidos");
        }

        Token token = this.gerarToken(login);
        return token;
	}
	
	private Token gerarToken(String login) {
        String tokenString = Jwts.builder()
                .setSubject(login)
                .claim("login", login)
                .signWith(SignatureAlgorithm.HS512, "pastel")
                .compact();

        Token token = new Token(tokenString);

        return token;
    }

}
