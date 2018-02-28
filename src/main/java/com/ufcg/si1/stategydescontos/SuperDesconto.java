package com.ufcg.si1.stategydescontos;

import java.math.BigDecimal;

public class SuperDesconto implements Desconto {

	@Override
	public BigDecimal calculaDesconto(BigDecimal preco) {
		return preco.multiply(new BigDecimal(0.5));
	}

}
