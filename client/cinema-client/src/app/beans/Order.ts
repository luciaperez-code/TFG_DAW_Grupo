import { Card } from "./Card";
import { ItemsInOrder } from "./ItemsInOrder";
import { User } from "./User";


export class Order {
    idOrder : number;
    createdDate : string;
    status : string;
    card : Card;
    user : User;
    itemsInOrder : ItemsInOrder[];
    showDetails?: boolean;

    constructor (data: any){
        this.idOrder = data.idOrder;
        this.createdDate = data.createdDate;
        this.status = data.status;
        this.card = data.card;
        this.user = data.user;
        this.itemsInOrder = data.itemsInOrder;
    }
}