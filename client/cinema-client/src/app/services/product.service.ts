import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment";
import { JSONResponse } from "../beans/JSONResponse";
import { Card } from "../beans/Card";
import { Product } from "../beans/Product";
import { LineaPedidoDTO } from "../beans/LineaPedidoDTO";
import { catchError, map } from 'rxjs/operators';
import { Observable, forkJoin } from 'rxjs';
import { Order } from "../beans/Order";

@Injectable({
    providedIn: 'root'
})
export class ProductService{

    constructor (private http: HttpClient, private route: Router) {}

    getProducts(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCTS);
    }

    getProductById(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCT_BY_ID + id);
    }

    getProductByName(name : string): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_ORDER_BY_ID + name);
    }
/** 
    addProduct(product : Product): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_PRODUCT, product);
    }

    editProduct(id : number, product : Product): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_PRODUCT + id, product);
    }
    
    deleteProduct(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_PRODUCT + id);
    }*/

    getProductsByType(typeId : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCTS_BY_TYPE + typeId);
    }

    getProductsByRelatedFilm(filmId : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PRODUCTS_BY_RELATED_FILM + filmId);
    }

    private getProductByIdPipe(id : number): Observable<any> {
        return this.http.get<any>(environment.GET_PRODUCT_BY_ID + id).pipe(
            map(response => response.successResponse)
        )
    }

    getProductsAndQuantities(carrito: LineaPedidoDTO[]): Observable<any[]> {
        const productosConCantidad: Observable<any>[] = [];
        for (const item of carrito) {
          if (item.idProduct !== undefined) {
            const producto$ = this.getProductByIdPipe(item.idProduct);
            productosConCantidad.push(producto$.pipe(
              map(producto => {
                const cantidad = item.quantity ?? 0;
                const precio = producto.price ?? 0;
                return {
                  ...producto,
                  cantidad: cantidad,
                  precioTotal: cantidad * precio
                };
              })
            ));
          }
        }
        return forkJoin(productosConCantidad);
      }
}