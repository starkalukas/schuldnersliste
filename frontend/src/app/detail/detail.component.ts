import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BackendService} from "../shared/backend.service";
import {IDebtorDto, IEntryDto} from "../shared/model";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {

  debtors: IDebtorDto[];
  entries: IEntryDto[];

  constructor(
    private readonly route: ActivatedRoute,
    private readonly backend: BackendService
  ) {
    this.debtors = [];
    this.entries = [];
  }

  ngOnInit(): void {

    this.backend.get<IDebtorDto[]>(`debtor/getDebtorsByAccount/${this.route.snapshot.params['id']}`).then(
      value => {
        this.debtors = value;
      }
    );
  }

  showEntries(id: number): void {
    this.backend.get<IEntryDto[]>(`debtor/${id}/entries`).then(
      value => {
        this.entries = value;
      }
    )
  }

  handleOutput(event: number) {
    console.log(event);
  }
}
