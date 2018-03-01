import { Injectable, EventEmitter, OnInit } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';

import { Produto } from './produto.model';

@Injectable()
export class ProdutoService {

  private result: any;
  private produtos: Array<Produto>;

  constructor(private http: Http) {
    this.getProdutos().subscribe(resp => {
      this.produtos = resp;
    });
  }

  getProdutos() {
    return this.http
      .get('http://localhost:8081/produto')
      .map(result => (this.result = result.json().data as Array<Produto>));
  }

}
