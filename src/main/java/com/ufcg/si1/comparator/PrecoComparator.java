package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.math.BigDecimal;
import java.util.Comparator;

public class PrecoComparator implements Comparator<Produto> {
    
	/**
        * Compara os preços dos produtos
        * 
        * @param produto1 produto 1 onde o preço será comparado
        * @param produto2 produto 2 onde o preço será comparado
        * @return retorna 0 se os preços forem iguais, qualquer outro valor se forem diferentes
        */
	
	@Override
	public int compare(Produto produto1, Produto produto2) {
        BigDecimal preco1 = produto1.getPreco();
        BigDecimal preco2 = produto2.getPreco();

        return preco1.compareTo(preco2);
    }
	
}
