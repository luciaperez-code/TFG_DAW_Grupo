import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment.develop";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { Card } from "../beans/Card";
import { Login } from "../beans/Login";

@Injectable({
    providedIn: 'root'
})
export class LoginService{

    constructor (private http: HttpClient, private route: Router) {}

    login(login : Login): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.LOGIN, login);
    }


}