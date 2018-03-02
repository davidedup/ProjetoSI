import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoteRoutingModule } from './lote-routing.module';
import { CadastraLoteComponent } from './cadastra-lote/cadastra-lote.component';
import { ListaLotesComponent } from './lista-lotes/lista-lotes.component';

@NgModule({
  imports: [
    CommonModule,
    LoteRoutingModule
  ],
  declarations: [CadastraLoteComponent, ListaLotesComponent]
})
export class LoteModule { }
