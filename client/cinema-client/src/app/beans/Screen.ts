export class Screen {
    idScreen : number;
    screenType : string;
    normalSeats : string;
    specialSeats : string;


    constructor (data: any){
        this.idScreen = data.idScreen;
        this.screenType = data.screenType;
        this.normalSeats = data.normalSeats;
        this.specialSeats = data.specialSeats;
    }
}