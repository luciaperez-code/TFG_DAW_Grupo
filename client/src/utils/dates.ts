export function getDate(date: string | number | Date) {
    let _date: Date;
  
    if (typeof date === 'string') {
      const match = date.match(/^(\d{1,2})-(\d{1,2})-(\d{4})$/);
  
      if (!match) {
        throw new Error('Invalid date format. Please use "DD-MM-YYYY".');
      }
  
      const [, day, month, year] = match.map(Number);
      _date = new Date(year, month - 1, day);
    } else {
      _date = new Date(date);
    }
  
    const textDays = ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"];
    const dow = (_date.getDay() + 6) % 7; // 0 is Monday, 6 is Sunday
  
    return {
      fullYear: `${_date.getFullYear()}-${_date.getMonth() + 1}-${_date.getDate()}`,
      year: _date.getFullYear(),
      month: _date.getMonth() + 1,
      day: _date.getDate(),
      dayOfWeek: dow,
      textDayOfWeek: textDays[dow],
    };
  }
  