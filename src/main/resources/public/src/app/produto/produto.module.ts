import { ProdutoService } from './produto.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { ListaProdutosComponent } from './lista-produtos/lista-produtos.component';
import { CadastraProdutoComponent } from './cadastra-produto/cadastra-produto.component';

@NgModule({
  imports: [
    CommonModule,
    ProdutoRoutingModule
  ],
  declarations: [ListaProdutosComponent, CadastraProdutoComponent],
  providers: [ProdutoService]
})
export class ProdutoModule { }
