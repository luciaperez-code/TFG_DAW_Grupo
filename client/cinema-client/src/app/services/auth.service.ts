import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { AuthRequest } from "../beans/AuthRequest";

@Injectable({
    providedIn: 'root'
})
export class AuthService{

    constructor (private http: HttpClient, private route: Router) {}

    login(login : AuthRequest): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.LOGIN, login);
    }

}