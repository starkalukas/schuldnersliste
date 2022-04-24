import {Injectable} from '@angular/core';
import {firstValueFrom} from "rxjs";
import {HttpClient} from "@angular/common/http";

const BASE_URL = 'http://localhost:8080'

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  constructor(
    private readonly http: HttpClient
  ) {
  }

  public get<T>(route: string): Promise<T> {
    return firstValueFrom(this.http.get<T>(`${BASE_URL}/${route}`));
  }

  public post<T>(route: string, body: any): Promise<T> {
    return firstValueFrom(this.http.post<T>(`${BASE_URL}/${route}`, body));
  }
}
