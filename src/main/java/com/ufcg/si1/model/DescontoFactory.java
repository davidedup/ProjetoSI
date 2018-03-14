package com.ufcg.si1.model;

import java.math.BigDecimal;

import exceptions.ObjetoInexistenteException;

public class DescontoFactory {
	
	public BigDecimal criaDesconto(String nomeDoDesconto) throws ObjetoInexistenteException {
		BigDecimal desconto = null;
		nomeDoDesconto = nomeDoDesconto.toLowerCase();
		
		switch (nomeDoDesconto) {
		case "sem-desconto":
			desconto = new BigDecimal(1);
			break;
		case "bom-desconto":
			desconto = new BigDecimal(0.90);
			break;
		case "otimo-desconto":
			desconto = new BigDecimal(0.75);
			break;
		case "super-desconto":
			desconto = new BigDecimal(0.50);
			break;
		default:
			throw new ObjetoInexistenteException("Nome de desconto inexistente");
		}
		
		return desconto;
	}

}
