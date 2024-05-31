import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';
import { JSONResponse } from '../beans/JSONResponse';
import { Projection } from '../beans/Projection';
import { LineaPedidoDTO } from '../beans/LineaPedidoDTO';
import { catchError, map } from 'rxjs/operators';
import { Observable, forkJoin } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProjectionService {
  constructor(private http: HttpClient, private route: Router) {}

  getProjections(): Observable<JSONResponse> {
    return this.http.get<JSONResponse>(environment.GET_PROJECTIONS);
  }

  getProjectionById(id: number): Observable<JSONResponse> {
    return this.http.get<JSONResponse>(environment.GET_PROJECTION_BY_ID + id);
  }

  private getProjectionByIdPipe(id: number): Observable<any> {
    return this.http
      .get<any>(environment.GET_PROJECTION_BY_ID + id)
      .pipe(map((response) => response.successResponse));
  }

  getProjectionByFilmId(id: number): Observable<JSONResponse> {
    return this.http.get<JSONResponse>(
      environment.GET_PROJECTION_BY_FILMID + id
    );
  }

  /**    addProjection(projection : Projection): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.ADD_PROJECTION, projection);
    }

    editProjection(id : number, projection : Projection): Observable<JSONResponse> {
        return this.http.post<JSONResponse>(environment.EDIT_PROJECTION + id, projection);
    }
    
    deleteProjection(id : number): Observable<JSONResponse> {
        return this.http.get<JSONResponse>(environment.DELETE_PROJECTION + id);
    }*/

  getProjectionsByDate(date: string): Observable<JSONResponse> {
    return this.http.get<JSONResponse>(
      environment.GET_PROJECTION_BY_DATE + date
    );
  }

  getProjectionsAndQuantities(carrito: LineaPedidoDTO[]): Observable<any[]> {
    const proyeccionesConCantidad: Observable<any>[] = [];
    for (const item of carrito) {
      if (item.idProjection !== undefined) {
        const projection$ = this.getProjectionByIdPipe(item.idProjection);
        proyeccionesConCantidad.push(
          projection$.pipe(
            map((projection) => {
              console.log('Lo que tengo: ', projection);
              const cantidad = item.quantity ?? 0;
              const precio = projection.price ?? 0;
              return {
                ...projection,
                cantidad: cantidad,
                precioTotal: cantidad * precio,
              };
            })
          )
        );
      }
    }
    return forkJoin(proyeccionesConCantidad);
  }
}
