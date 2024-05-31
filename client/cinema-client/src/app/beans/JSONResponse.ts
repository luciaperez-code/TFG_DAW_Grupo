import { Card } from "./Card";
import { Film } from "./Film";
import { Order } from "./Order";
import { Product } from "./Product";
import { ProductType } from "./ProductType";
import { Projection } from "./Projection";
import { User } from "./User";

export class JSONResponse {
  success: boolean;
  errorMessage: string;
  code: number;
  successResponse: any;
  token: string;

  constructor (data: any){
    this.success = data.success;
    this.errorMessage = data.errorMessage;
    this.code = data.code;
    this.successResponse = data.successResponse;
    this.token = data.token;
  }
}
