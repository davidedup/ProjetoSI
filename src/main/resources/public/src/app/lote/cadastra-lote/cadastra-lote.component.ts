import { Component, OnInit } from '@angular/core';
import { Lote } from '../lote.model';

@Component({
  selector: 'app-cadastra-lote',
  templateUrl: './cadastra-lote.component.html',
  styleUrls: ['./cadastra-lote.component.css']
})
export class CadastraLoteComponent implements OnInit {

  private lote: Lote;

  constructor() {
    this.lote = new Lote();
  }

  ngOnInit() {
  }

}
