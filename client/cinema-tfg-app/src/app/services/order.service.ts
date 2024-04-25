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
export class OrderService{

    constructor (private http: HttpClient, private route: Router) {}

    getOrders(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_ORDERS);
    }

    getOrderByIdUser(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_ORDERS_BY_USER + id);
    }

    getOrderById(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_ORDER_BY_ID + id);
    }


}