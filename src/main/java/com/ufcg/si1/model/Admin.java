package com.ufcg.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_admin")
public class Admin {
	
	@Id
	@Column(name = "login")
	private String login;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "senha")
	private String senha;
	
	public Admin() {
		login = "";
	}
	
	/**
         * Construtor da classe Admin
         * 
         * @param login login do Admin
         * @param nome nome do admin
         * @param senha senha do admin
         */
	public Admin(String login, String nome, String senha) {
		this.login = login;
		this.nome = nome;
		this.senha = senha;
	}
        
        /**
         * Retorna o login do admin
         * 
         * @return login do Admin
         */
        
	public String getLogin() {
		return this.login;
	}
        
        /**
         * Altera o login do Admin
         * 
         * @param login para ser alterado
         */
        
	public void setLogin(String login) {
		this.login = login;
	}
        
        /**
         * Get do nome do admin
         *
         * @return nome do admin
         */
        
	public String getNome() {
		return this.nome;
	}
        
        /**
         * Altera o nome do usuário
         * 
         * @param nome para ser alterado
         */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}
        /**
         * Altera a senha do admin
         * 
         * @param senha a ser alterada
         */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}

