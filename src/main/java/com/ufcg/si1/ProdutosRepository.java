package com.ufcg.si1;

import com.ufcg.si1.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutosRepository extends CrudRepository<Produto, Long> {

    

}
