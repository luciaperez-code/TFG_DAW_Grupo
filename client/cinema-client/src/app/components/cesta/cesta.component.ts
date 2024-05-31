import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JSONResponse } from 'src/app/beans/JSONResponse';
import { LineaPedidoDTO } from 'src/app/beans/LineaPedidoDTO';
import { Order } from 'src/app/beans/Order';
import { CarritoService } from 'src/app/services/carrito.service';
import { HttpService } from 'src/app/services/http.service';
import { environment } from 'src/environments/environment';
import { ProductsComponent } from '../products/products.component';
import { ProductService } from 'src/app/services/product.service';
import { forkJoin } from 'rxjs';
import { Product } from 'src/app/beans/Product';
import { ProjectionService } from 'src/app/services/projection.service';
import { DateService } from 'src/app/services/date.service';
import { Card } from 'src/app/beans/Card';

@Component({
  selector: 'app-cesta',
  templateUrl: './cesta.component.html',
  styleUrls: ['./cesta.component.css'],
})
export class CestaComponent implements OnInit {
  carrito: LineaPedidoDTO[] = [];
  token: string | null = sessionStorage.getItem('token');
  createdOrder: Order | null = null;
  products: any[] = [];
  projections: any[] = [];
  cards: Card[] = []; 
  selectedCardIndex: number | null = null;
  gracias: boolean = false;
  totalPrice: number = 0;

  constructor(
    private carritoService: CarritoService,
    private httpService: HttpService,
    private router: Router,
    private productService: ProductService,
    private projectionService: ProjectionService,
    private dateService: DateService
  ) {}

  ngOnInit() {
    if (sessionStorage.getItem('cart') == null){
      this.carritoService.initCarritoFromBD();
      console.log("Pasa por aqui");
    }else{
      this.initCarrito();
    }
    this.gracias = false;
    this.getProductsAndQuantities();
    this.getProjectionsAndQuantities();
    this.getCards();
  }

  getCards() {
    if (this.token != null) {
      this.httpService
        .getWithAuthorization<JSONResponse>(environment.GET_CARDS, this.token)
        .subscribe((response) => {
          if (response && response.successResponse) {
            this.cards = response.successResponse;
          } else {
            console.error('El usuario no tiene tarjetas');
          }
        });
    }
  }

  initCarrito() {
    const savedCart = sessionStorage.getItem('cart');
    if (savedCart != null && savedCart.length > 2) {
      this.carrito = JSON.parse(savedCart);
      this.carritoService.saveCart();
      console.log('Hay carrito en sesión' + savedCart);
    } else if (this.token != null) {
      
    } else {
      this.carrito = [];
    }
  }

  clearCart() {
    this.carrito = this.carritoService.clearCart();
  }

  calculateTotalPrice(): void {
    let productTotal = this.products.reduce(
      (sum, product) => sum + product.precioTotal,
      0
    );
    let projectionTotal = this.projections.reduce(
      (sum, projection) => sum + projection.precioTotal,
      0
    );
    this.totalPrice = productTotal + projectionTotal;
  }

  pagar() {
    if (this.token != null) {
      if (this.selectedCardIndex == null){
        window.alert("No puede continuar sin una tarjeta seleccionada!");
      }else{
             this.httpService
        .postWithAuthorization<JSONResponse>(
          environment.BUY,
          this.carrito,
          this.token
        )
        .subscribe((response) => {
          if(response.code == 404){
            window.alert(response.errorMessage);
          }else if (response.code== 200){
            this.createdOrder = response.successResponse as Order;
            this.clearCart();
            window.alert('Your cart has been saved!');
            this.gracias = true;
          }
        });
      }
 
    }
  }

  irARegistro() {
    this.router.navigate(['/registro']);
  }

  irALogin() {
    this.router.navigate(['/login']);
  }

  selectCard(index: number) {
    this.selectedCardIndex = index;
    if (this.selectedCardIndex !== null) {
      const selectedCard = this.cards[this.selectedCardIndex];
      this.carrito.forEach((item) => {
        item.idCard = selectedCard.idCard;
      });
    }
    this.carritoService.updateCartToSession(this.carrito);
  }

  getProductsAndQuantities() {
    this.productService.getProductsAndQuantities(this.carrito).subscribe(
      (products) => {
        this.products = products;
        this.calculateTotalPrice();
      },
      (error) => {
        console.error('Error al obtener los productos:', error);
      }
    );
  }

  getProjectionsAndQuantities() {
    this.projectionService.getProjectionsAndQuantities(this.carrito).subscribe(
      (projections) => {
        this.projections = projections;
        this.calculateTotalPrice();
        this.projections.forEach((projection) => {
          const date = new Date(projection.date);
          projection.date = this.dateService.formatDateShort(date);
        });
        console.log(projections);
      },
      (error) => {
        console.error('Error al obtener los productos:', error);
      }
    );
  }

  removeFromCart(idProduct: number) {
    this.carritoService.removeFromCart(idProduct);
    console.log('Item a borrar ' + idProduct);
    this.carrito = this.carritoService.getItems();
    console.log(this.carrito);
    this.getProductsAndQuantities();
    window.location.reload();
  }
}
