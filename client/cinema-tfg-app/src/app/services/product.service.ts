import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment.develop";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { Card } from "../beans/Card";
import { Product } from "../beans/Product";

@Injectable({
    providedIn: 'root'
})
export class ProductService{

    constructor (private http: HttpClient, private route: Router) {}

    getProduct(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCT);
    }

    getProductById(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCT_BY_ID + id);
    }

    getProductByName(name : string): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_ORDER_BY_ID + name);
    }

    addProduct(product : Product): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_PRODUCT, product);
    }

    editProduct(id : number, product : Product): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_PRODUCT + id, product);
    }
    
    deleteProduct(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_PRODUCT + id);
    }







}