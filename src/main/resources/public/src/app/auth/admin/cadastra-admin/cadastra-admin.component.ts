import { Component, OnInit } from '@angular/core';

import { Admin } from '../admin.model';

@Component({
  selector: 'app-cadastra-admin',
  templateUrl: './cadastra-admin.component.html',
  styleUrls: ['./cadastra-admin.component.css']
})
export class CadastraAdminComponent implements OnInit {

  private admin: Admin;

  constructor() {
    this.admin = new Admin();
  }

  ngOnInit() {
  }

}
