import { ProductType } from "./ProductType";


export class Product {
    idProduct : number;
    name : string;
    image : string;
    description : string;
    price : number;
    stock: number;
    productType : ProductType;
    idFilm : number | null;

    constructor (data: any){
        this.idProduct = data.idProduct;
        this.name = data.name;
        this.image = data.image;
        this.description = data.description;
        this.price = data.price;
        this.stock = data.stock;
        this.productType = data.productType;
        this.idFilm = data.idFilm;
    }
}