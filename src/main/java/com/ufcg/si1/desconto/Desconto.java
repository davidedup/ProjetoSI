package com.ufcg.si1.desconto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public interface Desconto {

	public BigDecimal calculaDesconto(BigDecimal preco);

}
