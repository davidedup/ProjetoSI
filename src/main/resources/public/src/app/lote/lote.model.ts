import { Produto } from './../produto/produto.model';

export class Lote {
  id: number;
  produto: Produto;
  numeroDeItens: number;
  dataDeValidade: Date;

  constructor() {

  }
}
