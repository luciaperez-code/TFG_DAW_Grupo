export class JSONRequest {
  success: boolean;
  errorMessage: string;
  code: number;
  successResponse: object;
  request: string;
  token : string;

  constructor (data: any){
    this.success = data.success;
    this.errorMessage = data.errorMessage;
    this.code = data.code;
    this.successResponse = data.successResponse;
    this.request = data.request;
    this.token = data.token;
  }
}
