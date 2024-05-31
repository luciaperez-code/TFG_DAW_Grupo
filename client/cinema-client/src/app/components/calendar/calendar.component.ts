import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Projection } from 'src/app/beans/Projection';
import { DateService } from 'src/app/services/date.service';
import { ProjectionService } from 'src/app/services/projection.service';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  projections! : Projection[];
  availableDays: string[] = []; // Array de días disponibles
  selectedDay!: string; // Día seleccionado por el usuario
  filteredProjections: Projection[] = []; // Proyecciones filtradas según el día seleccionado

  constructor(private projectionService : ProjectionService, 
    private router: Router, private dateService : DateService) { }

  ngOnInit(): void {
    this.projectionService.getProjections().subscribe(response => { this.projections = response.successResponse;});
    console.log(this.projections);
    this.calculateAvailableDays();
  }

  calculateAvailableDays(): void {
    const today = new Date(); // Obtener la fecha actual
    for (let i = 0; i < 7; i++) {
      const nextDay = new Date(today);
      nextDay.setDate(today.getDate() + i);
      const formattedDate = this.dateService.formatDateShort(nextDay); // Formatear la fecha utilizando el servicio
      this.availableDays.push(formattedDate);
      console.log("Días disponibles: " + this.availableDays);
    }
  }

  onDaySelect(day: string): void {
    console.log('Día seleccionado desde el front:', day);
    this.selectedDay = day;
    const dateObject = this.dateService.parseDateFromString(day);
    if (dateObject) {
      console.log('Día seleccionado para enviar al back:', dateObject);
      this.projectionService.getProjectionsByDate(dateObject).subscribe(response => {
        if (response && 'successResponse' in response) {
          this.filteredProjections = response.successResponse;
          console.log('Proyecciones filtradas:', this.projections);
        } else {
          console.log('Response does not have a successResponse property');
        }
      });
    }else{
      console.log('No se pudo parsear la fecha');
    }

  }

  goToProjection(id: number) {
    this.router.navigate(['/detail-projection', id]);
  }
}
