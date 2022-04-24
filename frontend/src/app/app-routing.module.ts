import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DetailComponent} from "./detail/detail.component";
import {AccountComponent} from "./account/account.component";
import {CreateEntryComponent} from "./create-entry/create-entry.component";

const routes: Routes = [
  {path: 'debtor/:id', component: DetailComponent},
  {path: 'create', component: CreateEntryComponent},
  {path: '', component: AccountComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
