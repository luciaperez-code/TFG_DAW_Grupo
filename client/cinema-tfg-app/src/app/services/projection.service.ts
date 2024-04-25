import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment.develop";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { Projection } from "../beans/Projection";

@Injectable({
    providedIn: 'root'
})
export class ProjectionService{

    constructor (private http: HttpClient, private route: Router) {}

    getProjections(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PROJECTIONS);
    }

    getProjectionById(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PROJECTION_BY_ID + id);
    }

    searchProjection(name : string): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_PROJECTION_BY_NAME + name);
    }

    addProjection(projection : Projection): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_PROJECTION, projection);
    }

    editProjection(id : number, projection : Projection): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_PROJECTION + id, projection);
    }
    
    deleteProjection(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_PROJECTION + id);
    }

    getProjectionsByPriceAsc(): Observable<JSONResponse>{
        return this.http.get<JSONResponse>(environment.GET_PROJECTION_PRICE_ASC);
    }

    getProjectionsByPriceDesc(): Observable<JSONResponse>{
        return this.http.get<JSONResponse>(environment.GET_PROJECTION_PRICE_DESC);
    }







}