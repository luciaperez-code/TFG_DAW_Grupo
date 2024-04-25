export class JSONResponse {
  success: boolean;
  errorMessage: string;
  code: number;
  successResponse: object;
  interopRefId: string;
  tokenInvalid: boolean;
  permissions: string[];

  constructor (data: any){
    this.success = data.success;
    this.errorMessage = data.errorMessage;
    this.code = data.code;
    this.successResponse = data.successResponse;
    this.interopRefId = data.interopRefId;
    this.tokenInvalid = data.tokenInvalid;
    this.permissions = data.permissions;
  }
}
