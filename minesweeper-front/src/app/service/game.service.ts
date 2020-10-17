import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Game} from "../model/game";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class GameService {


  constructor(private http: HttpClient) {
  }

  public games(): Observable<Game[]> {
    return this.http.get<Game[]>(environment.API);
  }

  public create(): Observable<Game> {
    return null;
  }

  public update(): Observable<Game> {
    return null;
  }

  public cellClick(): Observable<Game> {
    return null;
  }

  public setFlag(): Observable<Game> {
    return null;
  }
}
