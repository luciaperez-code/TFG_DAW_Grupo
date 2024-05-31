import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { User } from "../beans/User";
import { AuthRequest } from "../beans/AuthRequest";

@Injectable({
    providedIn: 'root'
})
export class UserService{

    constructor (private http: HttpClient, private route: Router) {}

    getUser(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_USER);
    }

    register(user : User): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.REGISTER_USER, user);
    }

    editUser(id : number, user : User): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_USER + id, user);
    }

/**    getCardsByUser(idUser : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.LIST_CARDS_BY_USER + idUser);
    }*/ 
    
}