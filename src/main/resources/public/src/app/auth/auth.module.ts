import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginAdminComponent } from './admin/login-admin/login-admin.component';
import { CadastraAdminComponent } from './admin/cadastra-admin/cadastra-admin.component';

@NgModule({
  imports: [
    CommonModule,
    AuthRoutingModule
  ],
  declarations: [LoginAdminComponent, CadastraAdminComponent]
})
export class AuthModule { }
