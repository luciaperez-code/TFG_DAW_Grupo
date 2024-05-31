export class LineaPedidoDTO {
    idProduct?: number;
    idProjection?: number;
    quantity?: number;
    price: number;
    occupiedNormalSeats?: string;
    occupiedSpecialSeats?: string;
    idCard?: number;
  
    constructor (data: any){
      this.idProduct = data.idProduct;
      this.idProjection = data.idProjection;
      this.quantity = data.quantity;
      this.price = data.price;
      this.occupiedNormalSeats = data.occupiedNormalSeats;
      this.occupiedSpecialSeats = data.occupiedSpecialSeats;
      this.idCard = data.idCard;
    }
  }