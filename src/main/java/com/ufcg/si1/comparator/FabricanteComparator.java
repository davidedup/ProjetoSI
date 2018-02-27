package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.util.Comparator;

public class FabricanteComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Produto produto1 = (Produto) o1;
        Produto produto2 = (Produto) o2;
        String fabricante1 = produto1.getFabricante();
        String fabricante2 = produto2.getFabricante();

        return fabricante1.compareTo(produto2.getNome());
    }
}
