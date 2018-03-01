import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClienteViewRoutingModule } from './cliente-view-routing.module';
import { ClienteViewComponent } from './cliente-view.component';

@NgModule({
  imports: [
    CommonModule,
    ClienteViewRoutingModule
  ],
  declarations: [ClienteViewComponent]
})
export class ClienteViewModule { }
