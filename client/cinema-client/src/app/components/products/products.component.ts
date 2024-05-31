import { Component, OnInit } from '@angular/core';
import { LineaPedidoDTO } from 'src/app/beans/LineaPedidoDTO';
import { Product } from 'src/app/beans/Product';
import { ProductType } from 'src/app/beans/ProductType';
import { CarritoService } from 'src/app/services/carrito.service';
import { ProductService } from 'src/app/services/product.service';
import { ProductTypeService } from 'src/app/services/productType.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];
  filteredProducts: Product[] = [];
  productTypes: ProductType[] = [];
  selectedProductType: ProductType | null = null;
  minPrice: number | null = null;
  maxPrice: number | null = null;
  selectedProduct: Product | null = null;
  selectedQuantity = 1;
  item : LineaPedidoDTO | null = null;
  token: string | null = sessionStorage.getItem('token');

  constructor(private productService: ProductService, private productTypeService : ProductTypeService,
      private carritoService : CarritoService
  ) { }

  ngOnInit(): void {
    this.loadProducts();
    this.loadProductTypes();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe(response => {
      this.filteredProducts = response.successResponse;
    });
  }

  loadProductTypes(): void {
    this.productTypeService.getProductTypes().subscribe(response => { this.productTypes = response.successResponse;})
  }

  sortByPriceAsc(): void {
    this.filteredProducts.sort((a, b) => a.price - b.price);
  }

  sortByPriceDesc(): void {
    this.filteredProducts.sort((a, b) => b.price - a.price);
  }

  filterByProductType(event: any): void {
    const typeId = event.target.value;
    if (!typeId) {
      // Si no se selecciona ningún tipo, mostrar todos los productos
      this.loadProducts();
      return;
    }
  
    // Filtrar productos por el tipo seleccionado
    this.productService.getProductsByType(typeId).subscribe(response => {
      this.filteredProducts = response.successResponse;
    });
  }
  
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
}
