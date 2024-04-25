import { Film } from "./Film";


export class Projection {
    idProjection : number;
    startDate : string;
    endDate : string;
    price : number;
    occupiedNormalSeats : string;
    occupiedSpecialSeats : string;
    film = Film;
    screen = Screen;

    constructor (data: any){
        this.idProjection = data.idProjection;
        this.startDate = data.startDate;
        this.endDate = data.endDate;
        this.price = data.price;
        this.occupiedNormalSeats = data.occupiedNormalSeats;
        this.occupiedSpecialSeats = data.occupiedSpecialSeats;
        this.film = data.film;
        this.screen = data.screen;
    }
}