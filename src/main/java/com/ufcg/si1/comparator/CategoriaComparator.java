package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.util.Comparator;

public class CategoriaComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Produto produto1 = (Produto) o1;
        Produto produto2 = (Produto) o2;
        String categoria1 = produto1.getCategoria();
        String categoria2 = produto2.getCategoria();

        return categoria1.compareTo(categoria2);
    }
}
