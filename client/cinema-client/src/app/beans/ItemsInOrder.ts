import { Card } from "./Card";
import { Order } from "./Order";
import { Product } from "./Product";
import { ProductType } from "./ProductType";
import { Projection } from "./Projection";
import { User } from "./User";


export class ItemsInOrder {
    idItemsOrder : number;
    quantity : number;
    normalSeats: string;
    specialSeats:string;
    price : number;
    order : Order;
    product : Product;
    projection : Projection;

    constructor (data: any){
        this.idItemsOrder = data.idItemsOrder;
        this.quantity = data.quantity;
        this.normalSeats = data.normalSeats;
        this.specialSeats = data.specialSeats;
        this.price = data.price;
        this.order = data.order;
        this.product = data.product;
        this.projection = data.projection;
    }
}