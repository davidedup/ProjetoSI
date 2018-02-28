package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.util.Comparator;

public class FabricanteComparator implements Comparator<Produto> {
    
	@Override
	public int compare(Produto produto1, Produto produto2) {
        String fabricante1 = produto1.getFabricante();
        String fabricante2 = produto2.getFabricante();

        return fabricante1.compareTo(fabricante2);
    }
	
}
