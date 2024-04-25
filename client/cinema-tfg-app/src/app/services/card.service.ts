import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment.develop";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { Card } from "../beans/Card";

@Injectable({
    providedIn: 'root'
})
export class CardService{

    constructor (private http: HttpClient, private route: Router) {}

    getCards(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_CARDS);
    }

    addCard(card : Card): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_CARD, card);
    }

    deleteCard(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_CARD + id);
    }

    editCard(id : number, card : Card): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_CARD + id, card);
    }







}