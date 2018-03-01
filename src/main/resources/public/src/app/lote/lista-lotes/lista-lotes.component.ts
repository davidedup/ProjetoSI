import { Component, OnInit } from '@angular/core';
import { Lote } from '../lote.model';

@Component({
  selector: 'app-lista-lotes',
  templateUrl: './lista-lotes.component.html',
  styleUrls: ['./lista-lotes.component.css']
})
export class ListaLotesComponent implements OnInit {

  private lotes: Array<Lote>;

  constructor() {
    this.lotes = new Array<Lote>();
  }

  ngOnInit() {
  }

}
