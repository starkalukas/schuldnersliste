import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BackendService} from "../shared/backend.service";
import {IAccount, IDebtor, IDebtorDto, IEntryCreate} from "../shared/model";

@Component({
  selector: 'app-create-entry',
  templateUrl: './create-entry.component.html',
  styleUrls: ['./create-entry.component.scss']
})
export class CreateEntryComponent implements OnInit {

  formGroup: FormGroup;
  accounts: IAccount[];
  debtors: IDebtorDto[];
  selectedAccount: IAccount | null;
  selectedDebtor: IDebtorDto | null;

  constructor(private readonly backend: BackendService) {
    this.formGroup = this.createFormGroup();
    this.accounts = [];
    this.debtors = [];
    this.selectedAccount = null;
    this.selectedDebtor = null;
  }

  ngOnInit(): void {
    this.backend.get<IAccount[]>("account").then(
      value => {
        this.accounts = value;
        this.selectedAccount = this.accounts[0];
        this.selectionChanged();
      }
    );
  }

  private createFormGroup(): FormGroup {
    return new FormGroup({
      amount: new FormControl('', [
        Validators.required,
        Validators.min(0)
      ]),
      reason: new FormControl('', [
        Validators.required
      ]),
      date: new FormControl('', [
        Validators.required
      ])
    });
  }

  submit(): void {
    const entry: IEntryCreate = {
      date: this.formGroup.get('date')?.value,
      amount: this.formGroup.get('amount')?.value,
      reason: this.formGroup.get('reason')?.value,
      edited: false,
      debtor: {
        id: this.selectedDebtor!.id
      }
    }

    this.backend.post<IEntryCreate>("entry", { ...entry, date: this.formatDate(entry.date)})
      .then(value => {
        console.log(value);
        console.log('done')
      });
  }

  selectionChanged(): void {
    this.backend.get<IDebtorDto[]>(`debtor/getDebtorsByAccount/${this.selectedAccount!.id}`).then(
      value => {
        this.debtors = value;
        this.selectedDebtor = this.debtors[0];
      }
    );
  }

  private formatDate(date: Date): string {
    return `${ date.getFullYear() }-${ ('0' + (date.getMonth() + 1)).slice(-2) }-${ ('0' + (date.getDate())).slice(-2) }`;
  }
}
