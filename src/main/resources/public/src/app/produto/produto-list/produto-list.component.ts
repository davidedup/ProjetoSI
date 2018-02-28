import { Component, OnInit } from '@angular/core';
import { ProdutoService } from './../produto.service';
import { Produto } from '../produto.model';

@Component({
  selector: 'app-produto-list',
  templateUrl: './produto-list.component.html',
  styleUrls: ['./produto-list.component.css']
})
export class ProdutoListComponent implements OnInit {

  private produtos: Array<Produto> = new Array<Produto>();

  constructor(private projetoService: ProdutoService) { }

  ngOnInit() {
    this.projetoService.getProdutos().subscribe(resp => { console.log("oi " + resp) });
  }

}
