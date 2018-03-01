import { Component, OnInit } from '@angular/core';

import { Admin } from '../admin.model';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent implements OnInit {

  private admin: Admin;

  constructor() {
    this.admin = new Admin();
  }
  ngOnInit() {
  }

}
