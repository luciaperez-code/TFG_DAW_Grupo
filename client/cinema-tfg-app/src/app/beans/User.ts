import { Card } from "./Card";

export class User {
    idUser : number;
    name : string;
    surname : string;
    birthDate : Date;
    email: string;
    password: string;
    enabled : number;
    registerDate : Date;
    cards : Card [];

    constructor (data: any){
        this.idUser = data.idUser;
        this.name = data.name;
        this.surname = data.surname;
        this.birthDate = data.birthDate;
        this.email = data.email;
        this.password = data.password;
        this.enabled = data.enabled;
        this.registerDate = data.registerDate;
        this.cards = data.cards;
    }
}