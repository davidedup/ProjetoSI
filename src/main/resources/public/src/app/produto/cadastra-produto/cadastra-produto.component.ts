import { Component, OnInit } from '@angular/core';

import { Produto } from '../produto.model';
import { ProdutoService } from '../produto.service';

@Component({
  selector: 'app-cadastra-produto',
  templateUrl: './cadastra-produto.component.html',
  styleUrls: ['./cadastra-produto.component.css']
})
export class CadastraProdutoComponent implements OnInit {

  private produto: Produto;

  constructor(private produtoService: ProdutoService) {
    this.produto = new Produto();
  }

  ngOnInit() {
  }

}
