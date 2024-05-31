import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FilmsComponent } from './components/films/films.component';
import { HomeComponent } from './components/home/home.component';
import { FilmDetailComponent } from './components/film-detail/film-detail.component';
import { LoginComponent } from './components/login/login.component';
import { UserDetailComponent } from './components/user-detail/user-detail.component';
import { ProductsComponent } from './components/products/products.component';
import { ProjectionDetailComponent } from './components/projection-detail/projection-detail.component';
import { CarritoService } from './services/carrito.service';
import { CestaComponent } from './components/cesta/cesta.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { SobreNosotrosComponent } from './components/sobre-nosotros/sobre-nosotros.component';

const routes: Routes = [
  { path: 'films', component: FilmsComponent }, 
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'film/:id', component: FilmDetailComponent },
  { path: 'login', component: LoginComponent },
  { path: 'user-detail', component: UserDetailComponent },
  { path: 'products', component: ProductsComponent},
  { path: 'projection/:id', component: ProjectionDetailComponent},
  { path: 'projection-detail/:id', component: ProjectionDetailComponent},
  { path: 'cart', component: CestaComponent},  
  { path: 'calendar', component: CalendarComponent},  
  { path: 'sobre-nosotros', component: SobreNosotrosComponent},  
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
