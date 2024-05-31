import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Film } from 'src/app/beans/Film';
import { DateService } from 'src/app/services/date.service';
import { FilmService } from 'src/app/services/film.service';

@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css'],
})
export class FilmsComponent implements OnInit {
  listFilms!: Film[];
  releasedFilms!: Film[];
  comingSoonFilms!: Film[];

  constructor(private filmService: FilmService, private dateService : DateService, private router: Router) {}

  ngOnInit(): void {
    this.getFilms();
  }

  getFilmImageUrl(film: Film): string {
    // Aquí debes construir la URL completa de la imagen usando la propiedad de la película
    return `assets/${film.image}`;
  }

  getFilms() {  
      this.filmService.getFilms().subscribe(response => {
        if (response.successResponse) {
            this.listFilms = response.successResponse;
    
            // Obtener la fecha de hoy
            const today = new Date();
    
            // Filtrar las películas según la fecha de estreno
            this.releasedFilms = this.listFilms.filter(film => {
                const releaseDate = this.dateService.parseDateFilm(film.released);
                return releaseDate <= today;
            });
            console.log('ReleasedFilms: ', this.releasedFilms);

            this.comingSoonFilms = this.listFilms.filter(film => {
                const releaseDate = this.dateService.parseDateFilm(film.released);
                return releaseDate > today;
            });
            console.log('comingSoonFilms: ', this.comingSoonFilms);
        } else {
            console.error('Error al obtener la lista de películas.');
        }
    });
  }
  
  goToFilmDetail(idFilm: number): void {
    this.router.navigate(['/film', idFilm]);
  }

}
