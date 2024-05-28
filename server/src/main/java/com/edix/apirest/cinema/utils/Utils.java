package com.edix.apirest.cinema.utils;

import com.edix.apirest.cinema.entities.JSONResponse;

public class Utils {

	public static void createJSONResponseError (JSONResponse jsonResponse, String method, String clase, Exception e) {
		try {
			jsonResponse.setSuccess(false);
			jsonResponse.setErrorMessage("Error in method " + method + " in the class " + clase + ": " + e.getMessage());
		} catch (Exception ex) {
		}
	}
	
	public static void createJSONResponseFailed (JSONResponse jsonResponse, int code, String errorMessage) {
		try {
			jsonResponse.setSuccess(false);
			jsonResponse.setCode(code);
			jsonResponse.setErrorMessage(errorMessage);
		} catch (Exception ex) {
		}
	}
	
	public static void createJSONResponseOk (JSONResponse jsonResponse, Object body) {
		try {
			jsonResponse.setSuccess(true);
			jsonResponse.setCode(200);
			jsonResponse.setSuccessResponse(body);
		} catch (Exception ex) {
		}
	}
}
