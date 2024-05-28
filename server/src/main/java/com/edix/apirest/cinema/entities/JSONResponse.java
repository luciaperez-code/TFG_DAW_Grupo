
package com.edix.apirest.cinema.entities;

public class JSONResponse {
    private boolean success;
    private String errorMessage;
    private int code;
    private Object successResponse;
    private String token;
    
	public JSONResponse() {
		super();
	}

	public JSONResponse(boolean success, String errorMessage, int code, Object successResponse, String token) {
		super();
		this.success = success;
		this.errorMessage = errorMessage;
		this.code = code;
		this.successResponse = successResponse;
		this.token = token;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getSuccessResponse() {
		return successResponse;
	}

	public void setSuccessResponse(Object successResponse) {
		this.successResponse = successResponse;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	   
    
}
