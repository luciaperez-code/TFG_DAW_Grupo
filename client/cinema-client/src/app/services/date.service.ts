import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DateService {
  constructor() {}

  getDayOfWeek(date: Date): string {
    const daysOfWeek = ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'];
    return daysOfWeek[date.getDay()];
  }

  getMonthNameShort(date: Date): string {
    const monthNames = [
      'ene',
      'feb',
      'mar',
      'abr',
      'may',
      'jun',
      'jul',
      'ago',
      'sep',
      'oct',
      'nov',
      'dic',
    ];
    return monthNames[date.getMonth()];
  }

  getMonthName(date: Date): string {
    const monthNames = [
      'Enero',
      'Febrero',
      'Marzo',
      'Abril',
      'Mayo',
      'Junio',
      'Julio',
      'Agosto',
      'Septiembre',
      'Octubre',
      'Noviembre',
      'Diciembre',
    ];
    return monthNames[date.getMonth()];
  }

  formatDateString(date: Date): string {
    const day = date.getDate();
    const month = this.getMonthName(date);
    const year = date.getFullYear();
    return `${day} ${month} ${year}`;
  }

  formatDateShort(date: Date): string {
    const dayOfWeek = this.getDayOfWeek(date);
    const day = date.getDate();
    const monthShort = this.getMonthNameShort(date);
    return `${dayOfWeek} ${day} ${monthShort}`;
  }

  //Calcula los días disponibles para la proyección

  formatDate(date: Date): string {
    // Lógica para formatear la fecha en el formato deseado (por ejemplo, "YYYY-MM-DD")
    return `${date.getFullYear()}-${this.padZero(
      date.getMonth() + 1
    )}-${this.padZero(date.getDate())}`;
  }

  padZero(num: number): string {
    return num < 10 ? '0' + num : num.toString();
  }

  parseDateFromString(dateString: string): string | null {
    const parts = dateString.split(' ');

    if (parts.length < 3) {
      return null;
    }

    const monthMapping: { [key: string]: string } = {
        ene: '01',
        feb: '02',
        mar: '03',
        abr: '04',
        may: '05',
        jun: '06',
        jul: '07',
        ago: '08',
        sep: '09',
        oct: '10',
        nov: '11',
        dic: '12',
      };

    const day = parseInt(parts[1]);
    const month = monthMapping[parts[2].toLowerCase()];
    const year = new Date().getFullYear();

    return year+"-"+month+"-"+day;
  }

  parseDateFilm(dateString: string): Date {
    const parts = dateString.split('-');
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10) - 1; // Los meses en JavaScript son 0-indexados
    const year = parseInt(parts[2], 10);
    return new Date(year, month, day);
}
}
