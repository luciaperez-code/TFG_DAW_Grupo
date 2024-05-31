import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LineaPedidoDTO } from '../beans/LineaPedidoDTO';
import { HttpService } from './http.service';
import { environment } from 'src/environments/environment';
import { JSONResponse } from '../beans/JSONResponse';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private items: LineaPedidoDTO[] = [];
  token: string | null = sessionStorage.getItem('token');

  constructor(private http: HttpClient, private httpService : HttpService) { 
    const savedCart = sessionStorage.getItem('cart');
    if (savedCart) {
      this.items = JSON.parse(savedCart);
    }
  }

  addToCart(item: LineaPedidoDTO) {
    this.items.push(item);
    this.saveCartToSession();
  }

  getItems(): LineaPedidoDTO[] {
    return this.items;
  }

  clearCart(): LineaPedidoDTO[] {
    this.items = [];
    this.saveCartToSession();
    return this.items;
  }

  saveCartToSession() {
    sessionStorage.setItem('cart', JSON.stringify(this.items));
    console.log(sessionStorage.getItem('cart'));
  }

  updateCartToSession(cart : LineaPedidoDTO[]) {
    sessionStorage.setItem('cart', JSON.stringify(cart));
  }

  saveCart() {
    const savedCart = sessionStorage.getItem('cart');
    if (savedCart) {
      this.items = JSON.parse(savedCart);
    }
    if (this.token != null){
        return this.httpService.postWithAuthorization<JSONResponse>(environment.ADD_SHOPPING_CART, this.items, this.token)
        .subscribe(response => {
          console.log('Esto ha pasado:', response.successResponse);
        }, error => {
          console.error('Error:', error);
          if (error.error && error.error.message) {
            console.log(error.error.message);
          } else {
            console.log('Error al procesar la solicitud');
          }
        });
    }
    return window.alert('No se ha podido guardar el carrito :(');
  }

  saveCartFromSession(){
    const savedCart = sessionStorage.getItem('cart');
    if (this.token != null && savedCart != null){
      this.httpService.postWithAuthorization<JSONResponse>(environment.ADD_SHOPPING_CART, this.items, this.token);
    }
  }

  removeFromCart(idProduct: number) {
    console.log("El id recibido a borrar es " + idProduct);
    this.items = this.items.filter(item => item.idProduct !== idProduct);
    console.log("Producto borrado " + this.items);
    this.saveCartToSession();
    this.saveCart();
  }

  initCarritoFromBD(){
    this.token = sessionStorage.getItem('token');
    console.log("¿Pasa por aqui?");
    if(this.token != null){
        this.httpService.getWithAuthorization<JSONResponse>(
          environment.GET_SHOPPING_CART,
          this.token
        )
        .pipe(
          map(response => response.successResponse?.itemsInOrder ?? []) // Extraer itemsInOrder o devolver un array vacío si no existe
        ).subscribe((items: any[]) => {
          if (items.length > 0) {
            this.items = items;
            console.log('Carrito obtenido:', this.items);
            this.updateCartToSession(items);
          } else {
            console.error('El usuario no tiene nada en la cesta');
            this.saveCartToSession();
          }
        });
    }

  }
}
