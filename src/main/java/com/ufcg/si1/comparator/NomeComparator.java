package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.util.Comparator;

     /**
     * Compara os nomes dos produtos
     * 
     * @param produto1 produto 1 onde o nome será comparado
     * @param produto2 produto 2 onde o nome será comparado
     * @return retorna 0 se os nomes forem iguais, qualquer outro valor se forem diferentes
     */

public class NomeComparator implements Comparator<Produto> {
    
	@Override
	public int compare(Produto produto1, Produto produto2) {
        String nome1 = produto1.getNome();
        String nome2 = produto2.getNome();

        return nome1.compareTo(nome2);
    }
	
}
