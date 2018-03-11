package com.ufcg.si1.desconto;

public class DescontoFactory {
	
	
	public Desconto criaDesconto(String nomeDoDesconto) {
		Desconto desconto = null;
		
		if(nomeDoDesconto.equalsIgnoreCase("sem desconto")) {
			desconto = new SemDesconto();
		} else if(nomeDoDesconto.equalsIgnoreCase("bom desconto")) {
			desconto = new BomDesconto();
		} else if (nomeDoDesconto.equalsIgnoreCase("otimo desconto")) {
			desconto = new OtimoDesconto();
		}else {
			desconto =  new SuperDesconto();
		}
		
		return desconto;
	}

}
