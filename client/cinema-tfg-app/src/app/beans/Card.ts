export class Card {
    idCard : number;
    number : number;
    holderName : string;
    expirationDate : string;
    cvv : number;

    constructor (data: any){
        this.idCard = data.idCard;
        this.number = data.number;
        this.holderName = data.holderName;
        this.expirationDate = data.expirationDate;
        this.cvv = data.cvv;
    }
}