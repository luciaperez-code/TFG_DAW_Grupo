import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Film } from 'src/app/beans/Film';
import { LineaPedidoDTO } from 'src/app/beans/LineaPedidoDTO';
import { Product } from 'src/app/beans/Product';
import { Projection } from 'src/app/beans/Projection';
import { Screen } from 'src/app/beans/Screen';
import { CarritoService } from 'src/app/services/carrito.service';
import { DateService } from 'src/app/services/date.service';
import { FilmService } from 'src/app/services/film.service';
import { ProductService } from 'src/app/services/product.service';
import { ProjectionService } from 'src/app/services/projection.service';

@Component({
  selector: 'app-film-detail',
  templateUrl: './film-detail.component.html',
  styleUrls: ['./film-detail.component.css']
})
export class FilmDetailComponent implements OnInit {
  
  e : any;
  film! : Film;
  projections! : Projection[];
  availableDays: string[] = []; // Array de días disponibles
  selectedDay!: string; // Día seleccionado por el usuario
  filteredProjections: Projection[] = []; // Proyecciones filtradas según el día seleccionado
  visibleDays: string[] = [];
  products: Product[] = [];
  selectedProduct: Product | null = null;
  selectedQuantity = 1;
  item : LineaPedidoDTO | null = null;
  token: string | null = sessionStorage.getItem('token');

  constructor(private filmService: FilmService,private route: ActivatedRoute, private projectionService : ProjectionService,
    private dateService: DateService, private productService: ProductService, private router: Router,
    private carritoService : CarritoService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id'); 
    if (id !== null) {
      const idNumber = +id; // Convierte id a número
      this.filmService.getFilmById(idNumber).subscribe(response => { this.film = response.successResponse;});

      this.projectionService.getProjectionByFilmId(idNumber).subscribe(response => { this.projections = response.successResponse;});
      this.productService.getProductsByRelatedFilm(idNumber).subscribe(response => { this.products = response.successResponse;});
    }  
    this.calculateAvailableDays();
  }
 
  calculateAvailableDays(): void {
    const today = new Date();
    for (let i = 0; i < 7; i++) {
      const nextDay = new Date(today);
      nextDay.setDate(today.getDate() + i);
      const formattedDate = this.dateService.formatDateShort(nextDay);
      this.availableDays.push(formattedDate);
    }
  }

  onDaySelect(day: string): void {
    console.log('Día seleccionado desde el front:', day);
    this.selectedDay = day;
    const dateObject = this.dateService.parseDateFromString(day);
    if (dateObject) {
      const selectedDate = new Date(dateObject);
      console.log('Día seleccionado desde el front:', day);
      this.filteredProjections = this.projections.filter(projection => {
        const projectionDate = new Date(projection.date);
        // Compara solo la fecha, ignorando la hora
        return projectionDate.toDateString() === selectedDate.toDateString();
      });

      console.log('Proyecciones filtradas:', this.filteredProjections);
    } else {
      // Si no hay fecha seleccionada, muestra todas las proyecciones
      this.filteredProjections = [];
    }

  }


  //Productos relacionados
  selectProduct(product: Product): void {
    this.selectedProduct = product;
    this.selectedQuantity = 1;
  }
  deselectProduct() {
    if (this.selectedProduct != null) {
      this.selectedProduct = null;
      this.selectedQuantity = 1;
    }
  }
  increaseQuantity() {
    this.selectedQuantity += 1;
  }

  decreaseQuantity() {
    if (this.selectedQuantity > 0) {
      this.selectedQuantity -= 1;
    }
  }

  addToCart() {
    if (this.token == null){
      window.alert('No puedes añadir cosas al carrito sin iniciar sesión :(');
    }else{
      if (this.selectedProduct != null){
        this.item = {
        idProduct: this.selectedProduct?.idProduct,
        quantity: this.selectedQuantity,
        price: this.selectedProduct?.price,
      };
      console.log(this.item);
      }
      if (this.item != null){
        this.carritoService.addToCart(this.item);
      }
      
      if(this.selectedProduct != null) {
        console.log(`${this.selectedProduct.name} añadido a la cesta! Cantidad: ${this.selectedQuantity}`);
        this.deselectProduct();
      }
    }

  }

  goToProjection(id: number) {
    this.router.navigate(['/detail-projection', id]);
  }
  
}
