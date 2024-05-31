import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { Projection } from "../beans/Projection";

@Injectable({
    providedIn: 'root'
})
export class ScreenService{

    constructor (private http: HttpClient, private route: Router) {}

    getScreen(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_SCREENS);
    }

    getScreenById(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_SCREEN_BY_ID + id);
    }

    getScreenType(type : string): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_SCREEN_TYPE + type);
    }
/** 
    addScreen(screen : Screen): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_SCREEN, screen);
    }*/

}