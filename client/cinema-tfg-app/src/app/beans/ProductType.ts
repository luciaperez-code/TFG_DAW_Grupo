export class ProductType {
    idProductType : number;
    name : string;

    constructor (data: any){
        this.idProductType = data.idProductType;
        this.name = data.name;
    }
}