package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Produto;

import java.math.BigDecimal;
import java.util.Comparator;

public class PrecoComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Produto produto1 = (Produto) o1;
        Produto produto2 = (Produto) o2;
        BigDecimal preco1 = produto1.getPreco();
        BigDecimal preco2 = produto2.getPreco();

        return preco1.compareTo(preco2);
    }
}
