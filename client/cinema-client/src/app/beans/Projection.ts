import { Film } from "./Film";


export class Projection {
    idProjection : number;
    date : string;
    startTime: string;
    endTime : string;
    price : number;
    occupiedNormalSeats : string;
    occupiedSpecialSeats : string;
    film : Film;
    screen = Screen;

    constructor (data: any){
        this.idProjection = data.idProjection;
        this.date = data.date;
        this.startTime = data.startTime;
        this.endTime = data.endTime;
        this.price = data.price;
        this.occupiedNormalSeats = data.occupiedNormalSeats;
        this.occupiedSpecialSeats = data.occupiedSpecialSeats;
        this.film = data.film;
        this.screen = data.screen;
    }
}