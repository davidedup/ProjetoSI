package com.ufcg.si1.desconto;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	@Override
	public BigDecimal calculaDesconto(BigDecimal preco) {
		return preco;
	}

}
