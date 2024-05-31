import { Component, HostListener, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { CarritoService } from 'src/app/services/carrito.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  token: string | null = sessionStorage.getItem('token');
  showLogout = false;
  menuOpen: boolean = false;
  isPopupVisible = false;

  constructor(public dialog: MatDialog, private carritoService : CarritoService) { }

  ngOnInit(): void {
  }

  openLoginPopup(): void {
      const dialogRef = this.dialog.open(LoginComponent, {
        width: '100px', // Ancho del diálogo
        data: {} // Datos que puedes pasar al componente de inicio de sesión si es necesario
      });

      dialogRef.componentInstance.tokenReceived.subscribe(() => {
        dialogRef.close(); // Cerrar el pop-up cuando se recibe el token
        window.location.reload(); // Recargar la página
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('El diálogo se ha cerrado');
        // Puedes realizar acciones después de cerrar el diálogo aquí si es necesario
      });
  }

  showUserInfo(): void {
    console.log('Token:', this.token);
  }
  
  logout(): void {
    this.carritoService.saveCartFromSession();
    sessionStorage.clear();    
    window.location.reload();
  }

  showLogoutButton(): void {
    this.showLogout = true;
  }

  hideLogoutButton(): void {
    this.showLogout = false;
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  openRegisterPopup(): void {
    const dialogRef = this.dialog.open(RegisterComponent, {
      width: '100px', 
      data: {} 
    });

    dialogRef.componentInstance.tokenReceived.subscribe(() => {
      dialogRef.close(); // Cerrar el pop-up cuando se recibe el token
      window.location.reload(); // Recargar la página
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('El diálogo se ha cerrado');
      // Puedes realizar acciones después de cerrar el diálogo aquí si es necesario
    });

  }

  showPopup(): void {
    this.isPopupVisible = true;
  }

  handleClose(): void {
    console.log('Cerrando popup en componente padre');
    this.isPopupVisible = false;
  }

  @HostListener('window:resize', ['$event'])
  onResize() {
    if(window.innerWidth > 995)
    this.menuOpen = false;
  }
}
