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
	
	public Admin(String login, String nome, String senha) {
		this.login = login;
		this.nome = nome;
		this.senha = senha;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
