import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "../../environments/environment";
import { JSONResponse } from "../beans/JSONResponse";
import { Observable } from "rxjs";
import { Film } from "../beans/Film";

@Injectable({
    providedIn: 'root'
})
export class FilmService{

    constructor (private http: HttpClient, private route: Router) {}

    getFilms(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_FILMS);
    }

    getReleasedFilms(): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.GET_RELEASED_FILMS);
    }

    getFilmById(id : number): Observable<JSONResponse>{
        return this.http.get<JSONResponse>(environment.GET_FILMS_BY_ID + id);
    }

    searchFilm(title : string): Observable<JSONResponse>{
        return this.http.get<JSONResponse>(environment.SEARCH_FILM + "search?title=" + title);
    }
 /** 
    addFilm(film : Film): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_FILM, film);
    }

    editFilm(id : number, film : Film): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_FILM + id, film);
    }
    
    deleteProduct(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_FILM + id);
    }*/








}