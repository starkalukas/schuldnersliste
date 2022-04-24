import { Component, OnInit } from '@angular/core';
import {IAccount} from "../shared/model";
import {BackendService} from "../shared/backend.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  accounts: IAccount[] | null;

  constructor(private readonly service: BackendService) {
    this.accounts = null;
  }

  ngOnInit(): void {
    this.service.get<IAccount[]>("account").then(value => {
      this.accounts = value;
    });
  }

}
