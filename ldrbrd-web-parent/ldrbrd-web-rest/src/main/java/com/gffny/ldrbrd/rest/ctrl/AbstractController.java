/**
 * 
 */
package com.gffny.ldrbrd.rest.ctrl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.WebContentGenerator;

import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.web.model.JSONable;
import com.gffny.ldrbrd.web.model.JsonResponse;
import com.gffny.ldrbrd.web.model.JsonResponse.JsonFailResponse;
import com.gffny.ldrbrd.web.model.JsonResponse.JsonSuccessResponse;
import com.gffny.ldrbrd.web.model.RequestContext;
import com.gffny.ldrbrd.web.model.ServletData;
//import com.sun.servicetag.UnauthorizedAccessException;
//import com.gffny.leaderboard.intralayer.ServiceException;
//import com.gffny.leaderboard.model.IGolfer;

public abstract class AbstractController extends WebContentGenerator {

	/**
	 * 
	 */
	public static final String INIT_RESPONSE_SESSION_KEY = "INIT_RESPONSE_SESSION_KEY";

	/**
	 * 
	 */
	protected static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 
	 * @return
	 */
	public GolferProfile getUser() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal != null && principal instanceof GolferProfile) {
			return (GolferProfile) principal;
		} else {
			// TODO should there be another way to return the user profile?
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public ServletData getServletData() {
		return RequestContext.get().getServletData();
	}

	// @ExceptionHandler(UnauthorizedAccessException.class)
	// protected ResponseEntity<JsonResponse<JSONable>>
	// handleUnauthorizedAccessException(
	// UnauthorizedAccessException ex) {
	// return returnError("Unauthorized Access: " + ex.getMessage(),
	// HttpStatus.UNAUTHORIZED);
	// }
	//
	// @ExceptionHandler(AccessDeniedException.class)
	// protected ResponseEntity<JsonResponse<JSONable>>
	// handleUnauthorizedAccessException(
	// AccessDeniedException ex) {
	// return returnError("Unauthorized Access: " + ex.getMessage(),
	// HttpStatus.UNAUTHORIZED);
	// }
	//
	// @ExceptionHandler(LockedException.class)
	// protected ResponseEntity<JsonResponse<JSONable>> handleLockedException(
	// LockedException ex) {
	// return returnError(ex.getMessage(), HttpStatus.LOCKED);
	// }
	//
	// @ExceptionHandler(Throwable.class)
	// protected ResponseEntity<JsonResponse<JSONable>>
	// handleUnexpectedException(
	// Throwable ex) {
	// ex.printStackTrace();
	// return returnError("Unexpected exception: " + ex.getMessage(),
	// HttpStatus.INTERNAL_SERVER_ERROR);
	// }
	//
	// @ExceptionHandler(JsonParseException.class)
	// protected ResponseEntity<JsonResponse<JSONable>> handleJsonException(
	// JsonParseException ex) {
	// return returnError("INVALID JSON: " + ex.getMessage(),
	// HttpStatus.BAD_REQUEST);
	// }
	//
	// @ExceptionHandler(JsonMappingException.class)
	// protected ResponseEntity<JsonResponse<JSONable>> handleJsonException(
	// JsonMappingException ex) {
	// return returnError("INVALID JSON: " + ex.getMessage(),
	// HttpStatus.BAD_REQUEST);
	// }
	//
	//
	// response methods
	//

	protected ResponseEntity<JsonResponse<JSONable>> returnSuccess(
			HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		JsonResponse<JSONable> jsonReponse = new JsonSuccessResponse<JSONable>();
		return new ResponseEntity<JsonResponse<JSONable>>(jsonReponse, headers,
				status);
	}

	protected ResponseEntity<JsonResponse<JSONable>> returnSuccess(
			JSONable response, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		JsonResponse<JSONable> jsonReponse = new JsonSuccessResponse<JSONable>(
				response);
		return new ResponseEntity<JsonResponse<JSONable>>(jsonReponse, headers,
				status);
	}

	protected ResponseEntity<JsonResponse<JSONable>> returnError(
			String message, HttpStatus status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		JsonResponse<JSONable> jsonReponse = new JsonFailResponse<JSONable>(
				message);
		return new ResponseEntity<JsonResponse<JSONable>>(jsonReponse, headers,
				status);
	}

}
