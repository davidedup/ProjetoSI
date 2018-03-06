package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.util.Comparator;

public class CategoriaComparator implements Comparator<Produto> {
    
	@Override
    public int compare(Produto produto1, Produto produto2) {
        String categoria1 = produto1.getCategoria().getCategoria();
        String categoria2 = produto2.getCategoria().getCategoria();

        return categoria1.compareTo(categoria2);
    }
	
}
