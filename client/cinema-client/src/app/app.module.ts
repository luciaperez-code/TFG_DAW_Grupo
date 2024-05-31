import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { FilmsComponent } from './components/films/films.component';
import { HttpClientModule } from '@angular/common/http';
import { FilmDetailComponent } from './components/film-detail/film-detail.component';
import { LoginComponent } from './components/login/login.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserDetailComponent } from './components/user-detail/user-detail.component';
import { CommonModule, DatePipe } from '@angular/common';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { ProductsComponent } from './components/products/products.component';
import { ProjectionDetailComponent } from './components/projection-detail/projection-detail.component';
import { RegisterComponent } from './components/register/register.component';
import { CestaComponent } from './components/cesta/cesta.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { SobreNosotrosComponent } from './components/sobre-nosotros/sobre-nosotros.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    FilmsComponent,
    FilmDetailComponent,
    LoginComponent,
    UserDetailComponent,
    ProductsComponent,
    ProjectionDetailComponent,
    RegisterComponent,
    CestaComponent,
    CalendarComponent,
    SobreNosotrosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatIconModule,
    ReactiveFormsModule,
    CommonModule,
    SlickCarouselModule
  ],
  providers: [
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
