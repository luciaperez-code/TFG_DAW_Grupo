import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment.develop";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { ProductType } from "../beans/ProductType";

@Injectable({
    providedIn: 'root'
})
export class ProductTypeService{

    constructor (private http: HttpClient, private route: Router) {}

    getProductType(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCTTYPES);
    }

    getProductTypeById(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCTTYPE_BY_ID + id);
    }

    addProductType(producttype : ProductType): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_PRODUCTTYPE, producttype);
    }

/** editFilm(id : number, product : Product): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_PRODUCT + id, product);
    }
    
    deleteProduct(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_PRODUCT + id);
    }*/







}