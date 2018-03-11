package com.ufcg.si1.comparator;

import com.ufcg.si1.model.Categoria;
import com.ufcg.si1.model.Produto;

import java.util.Comparator;

public class CategoriaComparator implements Comparator<Produto> {
    
     /**
     * Compara as categorias dos produtos
     * 
     * @param produto1 produto 1 onde a categoria será comparado
     * @param produto2 produto 2 onde a categoria será comparado
     * @return retorna 0 se as categorias forem iguais, qualquer outro valor para categorias diferentes
     */
	
	@Override
    public int compare(Produto produto1, Produto produto2) {
        Categoria categoria1 = produto1.getCategoria();
        Categoria categoria2 = produto2.getCategoria();

        return categoria1.compareTo(categoria2);
    }
	
}
