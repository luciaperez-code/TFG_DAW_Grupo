import { Card } from "./Card";
import { ItemsInOrder } from "./ItemsInOrder";
import { User } from "./User";


export class Login {
    email : string;
    password : string;

    constructor (data: any){
        this.email = data.email;
        this.password = data.password;
    }
}