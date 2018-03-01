import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PainelDeControleComponent } from './painel-de-controle.component';
import { RegistraVendaComponent } from './venda/registra-venda/registra-venda.component';
import { ListaVendasComponent } from './venda/lista-vendas/lista-vendas.component';

const routes: Routes = [
  { path: 'painel-de-controle', component: PainelDeControleComponent },
  { path: 'registrar-venda', component: RegistraVendaComponent },
  { path: 'vendas', component: ListaVendasComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PainelDeControleRoutingModule { }
