import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IEntryDto} from "../shared/model";

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.scss']
})
export class EntryComponent implements OnInit {

  @Output()
  output: EventEmitter<number> = new EventEmitter<number>();

  @Input()
  entries: IEntryDto[];

  constructor() {
    this.entries = [];
  }

  ngOnInit(): void {
  }

  returnAmount(amount: number): void {
    this.output.emit(amount);
  }
}
