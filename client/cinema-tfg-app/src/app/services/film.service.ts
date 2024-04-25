import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment.develop";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { Card } from "../beans/Card";
import { Film } from "../beans/Film";

@Injectable({
    providedIn: 'root'
})
export class FilmService{

    constructor (private http: HttpClient, private route: Router) {}

    getFilms(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_FILMS);
    }

    getFilmById(id : number): Observable<JSONResponse>{
        return this.http.get<JSONResponse>(environment.GET_FILMS_BY_ID + id);
    }

    addFilm(film : Film): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_FILM, film);
    }

    searchFilm(title : string): Observable<JSONResponse>{
        return this.http.get<JSONResponse>(environment.SEARCH_FILM + "/search?title=" + title);
    }
 
    editFilm(id : number, film : Film): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_FILM + id, film);
    }
    
    deleteProduct(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_FILM + id);
    }








}