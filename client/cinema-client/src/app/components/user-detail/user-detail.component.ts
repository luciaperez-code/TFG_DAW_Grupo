import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, Component, HostListener, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Card } from 'src/app/beans/Card';
import { JSONResponse } from 'src/app/beans/JSONResponse';
import { Order } from 'src/app/beans/Order';
import { User } from 'src/app/beans/User';
import { DateService } from 'src/app/services/date.service';
import { HttpService } from 'src/app/services/http.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css'],
})
export class UserDetailComponent implements OnInit {
  user: any;
  token = sessionStorage.getItem('token');
  showAddCardForm: boolean = false;
  newCard: Card = new Card({});
  addCardForm: FormGroup;
  submitted = false;
  error: string = '';
  isSmallScreen: boolean = false;
  minExpirationDate: string;
  pedidos : Order[] | null = null;

  constructor(private httpService: HttpService, private formBuilder: FormBuilder, private datePipe: DatePipe,
    private cdr: ChangeDetectorRef, private dateService : DateService) {
    this.addCardForm = this.formBuilder.group({
      number: ['', [Validators.required, Validators.minLength(18), Validators.maxLength(18), Validators.pattern(/^\d{18}$/)]],
      holderName: ['', [Validators.required]],
      expirationDate: ['', [Validators.required]],
      cvv: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3), Validators.pattern(/^\d{3}$/)]]
    });
    this.isSmallScreen = window.innerWidth < 768;
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
    this.minExpirationDate = `${year}-${month}-${day}`;
  }

  ngOnInit(): void {
    const idUser = sessionStorage.getItem('idUser');
    console.log('Iduser = ' + idUser);
    this.getUser();
    this.initForm();
  }

  get f() { return this.addCardForm.controls; }

  initForm(){
    this.addCardForm = this.formBuilder.group({
      number: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      holderName: ['', Validators.required],
      expirationDate: ['', Validators.required],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]]
    });
  }

  deleteCard(cardId: number): void {
    if(this.token){
      this.httpService.getWithAuthorization<JSONResponse>(environment.DELETE_CARD + cardId, this.token)
      .subscribe(response => {
        console.log('Tarjeta eliminada con éxito:', response.successResponse);
        window.location.reload();
      }, error => {
        console.error('Error al eliminar tarjeta:', error);
      });
    }
  }

  getUser(){
    if (this.token) {
      this.httpService.getWithAuthorization<JSONResponse>(environment.GET_USER,this.token).subscribe((response) => {
            console.log('Respuesta del servidor:', response);
            if (response && response.successResponse) {
              this.user = new User(response.successResponse);
              this.user.birthDate = this.dateService.formatDateString(new Date(this.user.birthDate));
              this.user.registerDate = this.dateService.formatDateString(new Date(this.user.registerDate));
              console.log('Usuario asignado:', this.user);
              this.getOrdersByUser(this.user.idUser);
            } else {
              console.error(
                'La respuesta del servidor no tiene datos válidos.'
              );
            }
          },
          (error) => {
            console.error('Error al obtener datos del usuario:', error);
          }
        );
    }
  }

  getOrdersByUser(idUser : number){
    if (this.token) {
      this.httpService.getWithAuthorization<JSONResponse>(environment.GET_ORDERS,this.token).subscribe((response) => {
            console.log('Respuesta del servidor:', response);
            if (response && response.successResponse) {
              this.pedidos = response.successResponse;
              if (this.pedidos != null) {
                this.pedidos.forEach((pedido) => {
                  pedido.showDetails = false; // Inicializar en false para ocultar los detalles por defecto
                });
              }
              console.log('Pedidos de este user:', this.pedidos);
            } else {
              console.error(
                'La respuesta del servidor no tiene datos válidos.'
              );
            }
          },
          (error) => {
            console.error('Error al cargar orders:', error);
          }
        );
    }
  }

  addCard(): void {
    if (this.token) {
      console.log('Tarjeta sin formatear: ' + this.newCard.expirationDate.toString());
      const expirationDate = new Date(this.newCard.expirationDate);
      this.newCard.expirationDate = this.formatDate(expirationDate);
      console.log('Fecha nueva:', this.newCard.expirationDate);
      console.log(this.newCard);
      this.httpService.postWithAuthorization(environment.ADD_CARD, this.newCard, this.token)
        .subscribe(response => {
          console.log('Tarjeta añadida con éxito:', response);
          // this.getCardList();
          this.resetForm();
          this.showAddCardForm = false;
          window.location.reload();
        }, error => {
          console.error('Error al añadir tarjeta:', error);
          if (error.error && error.error.message) {
            this.error = error.error.message;
          } else {
            this.error = 'Error al procesar la solicitud';
          }
        });
    }
  }

  openAddCardForm(): void {
    this.showAddCardForm = true;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.addCardForm.valid) {
      this.newCard = this.addCardForm.value;
      this.addCard();
    }else{

    }
  }

  cancelAddCard(): void {
    this.resetForm();
    this.showAddCardForm = false;
  }

  private resetForm(): void {
    this.submitted = false;
    this.error = '';
    this.newCard = new Card({});
    this.addCardForm.reset();
  }

  @HostListener('window:resize', ['$event'])
  onResize() {
    // Actualizar isSmallScreen cuando el tamaño de la ventana cambie
    this.isSmallScreen = window.innerWidth < 768;
  }

  formatDate(date: Date): string {
    // Utiliza DatePipe para formatear la fecha
    return this.datePipe.transform(date, 'dd-MM-yyyy') || '';
  }

  toggleDetails(index: number): void {
    if (this.pedidos && index >= 0 && index < this.pedidos.length) {
      this.pedidos[index].showDetails = !this.pedidos[index].showDetails;
      console.log("Pedidos: ", this.pedidos);
      this.cdr.detectChanges();
    }
  }
}
