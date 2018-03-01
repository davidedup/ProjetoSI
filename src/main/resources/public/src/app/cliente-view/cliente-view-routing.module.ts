import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ClienteViewComponent } from './cliente-view.component';

const routes: Routes = [
  { path: 'home', component: ClienteViewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClienteViewRoutingModule { }
