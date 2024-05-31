import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthRequest } from 'src/app/beans/AuthRequest';
import { User } from 'src/app/beans/User';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerRequest = {
    name: '',
    surname: '',
    birthDate: '',
    email: '',
    password: ''
  };
  error! : string;
  @Output() close: EventEmitter<void> = new EventEmitter<void>();
  @Output() tokenReceived: EventEmitter<void> = new EventEmitter<void>();

  constructor(private userService: UserService) {}

  ngOnInit(): void {
  }

  onSubmit(registerRequestData : any): void {
    this.userService.register(registerRequestData).subscribe(
      (response) => {
        if (response.success === false){
          this.error = "Usuario o contraseña incorrectos";
        }else{
          sessionStorage.setItem('token', response.token);
          sessionStorage.setItem('idUser', response.successResponse.idUser);
          this.onTokenReceived();
          this.closePopup();
        }
      },
      (error) => {
        this.error = "Error en la llamada";
      }
    );
  }

  closePopup(): void {
    console.log('Cerrando popup');
    this.close.emit();
  }

  // Lógica para manejar el token recibido
  onTokenReceived(): void {
    this.tokenReceived.emit();
  }

}
