import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastraLoteComponent } from './cadastra-lote/cadastra-lote.component';
import { ListaLotesComponent } from './lista-lotes/lista-lotes.component';

const routes: Routes = [
  { path: 'cadastrar-lote', component: CadastraLoteComponent },
  { path: 'lotes', component: ListaLotesComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoteRoutingModule { }
