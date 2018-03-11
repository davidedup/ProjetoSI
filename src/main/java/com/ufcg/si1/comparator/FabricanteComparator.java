package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.util.Comparator;

     /**
     * Compara as fabricantes dos produtos
     * 
     * @param produto1 produto 1 onde o fabricante será comparado
     * @param produto2 produto 2 onde o fabricante será comparado
     * @return retorna 0 se os fabricantes forem iguais, qualquer outro valor se forem diferentes
     */

public class FabricanteComparator implements Comparator<Produto> {
    
	@Override
	public int compare(Produto produto1, Produto produto2) {
        String fabricante1 = produto1.getFabricante();
        String fabricante2 = produto2.getFabricante();

        return fabricante1.compareTo(fabricante2);
    }
	
}
