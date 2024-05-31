import { Card } from "./Card";

export class User {
    idUser : number;
    name : string;
    surname : string;
    birthDate : string;
    email: string;
    enabled : number;
    registerDate : string;
    cards : Card [];

    constructor (data: any){
        this.idUser = data.idUser;
        this.name = data.name;
        this.surname = data.surname;
        this.birthDate = data.birthDate;
        this.email = data.email;
        this.enabled = data.enabled;
        this.registerDate = data.registerDate;
        this.cards = data.cards;
    }
}