import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  
  constructor(private http: HttpClient) { }

  get<JSONResponse>(url: string, options?: { headers?: HttpHeaders }): Observable<JSONResponse> {
    return this.http.get<JSONResponse>(url, options);
  }

  post<JSONResponse>(url: string, body: any, options?: { headers?: HttpHeaders }): Observable<JSONResponse> {
    return this.http.post<JSONResponse>(url, body, options);
  }

  getWithAuthorization<JSONResponse>(url: string, token: string): Observable<JSONResponse> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.get<JSONResponse>(url, { headers });
  }

  postWithAuthorization<JSONResponse>(url: string, body: any, token: string): Observable<JSONResponse> {
    let header = new HttpHeaders().set("Authorization", "Bearer " + token);
    console.log("Header: ", header);

    return this.post<JSONResponse>(url, body, { headers:header });
  }
}


