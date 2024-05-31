import { Component, EventEmitter, Output } from '@angular/core';
import { AuthRequest } from 'src/app/beans/AuthRequest';
import { AuthService } from 'src/app/services/auth.service';
import { CarritoService } from 'src/app/services/carrito.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  authRequest = { email: '', password: '' }; // Define authRequest
  @Output() close: EventEmitter<void> = new EventEmitter<void>();
  // Emitir un evento cuando se recibe el token correctamente
  @Output() tokenReceived: EventEmitter<void> = new EventEmitter<void>();
  error! : string;

  constructor(private authService: AuthService, private carritoService : CarritoService) { }

  onSubmit(authRequest: AuthRequest) {
    this.authService.login(authRequest).subscribe(response => {
      // Manejar la respuesta del servidor aquí
      console.log('Respuesta del servidor:', response);
      if (response.success === false){
        this.error = "Usuario o contraseña incorrectos";
      }else{
        sessionStorage.setItem('token', response.token);
        sessionStorage.setItem('idUser', response.successResponse.idUser);
        this.carritoService.initCarritoFromBD();
        this.onTokenReceived();
      }
    }, error => {
      // Manejar cualquier error de la llamada aquí
      console.error('Error en la llamada:', error);
    });
  }

  closePopup(): void {
    console.log('Cerrando popup');
    this.close.emit();
    console.log(this.close.emit());
  }

  // Lógica para manejar el token recibido
  onTokenReceived(): void {
    this.tokenReceived.emit();
  }
}
