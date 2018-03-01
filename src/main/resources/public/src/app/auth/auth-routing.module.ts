import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CadastraAdminComponent } from './admin/cadastra-admin/cadastra-admin.component';
import { LoginAdminComponent } from './admin/login-admin/login-admin.component';

const routes: Routes = [
  { path: 'cadastro', component: CadastraAdminComponent },
  { path: 'login', component: LoginAdminComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
