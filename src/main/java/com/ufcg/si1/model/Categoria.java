package com.ufcg.si1.model;

import com.ufcg.si1.stategydescontos.Desconto;
import com.ufcg.si1.stategydescontos.SemDesconto;

public class Categoria {
	
	String categoria;
	Desconto desconto;
	
	
	
	public Categoria(String categoria) {
		this.categoria = categoria;
		this.desconto = new SemDesconto();
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Desconto getDesconto() {
		return desconto;
	}

	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}

}