package com.gffny.ldrbrd.web.model;

import com.gffny.ldrbrd.web.model.JSONable;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public abstract class JsonResponse<RESPONSE_ENTITY extends JSONable> {

	private enum Status {
		SUCCESS, FAIL, ERROR;
	}

	public abstract Status getStatus();

	public static class JsonSuccessResponse<RESPONSE_ENTITY extends JSONable>
			extends JsonResponse<RESPONSE_ENTITY> {

		private RESPONSE_ENTITY payload;

		@Override
		public Status getStatus() {
			return Status.SUCCESS;
		}

		public JsonSuccessResponse() {
			setPayload(null);
		}

		public JsonSuccessResponse(RESPONSE_ENTITY payload) {
			setPayload(payload);
		}

		/**
		 * @return the payload
		 */
		public RESPONSE_ENTITY getPayload() {
			return payload;
		}

		/**
		 * @param payload
		 *            the payload to set
		 */
		public void setPayload(RESPONSE_ENTITY payload) {
			this.payload = payload;
		}

	}

	public static class JsonFailResponse<RESPONSE_ENTITY extends JSONable>
			extends JsonResponse<RESPONSE_ENTITY> {

		private String message;

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.carat.services.core.ui.JsonResponse#getStatus()
		 */
		@Override
		public Status getStatus() {
			return Status.FAIL;
		}

		public JsonFailResponse(String failureMessage) {
			setMessage(failureMessage);
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message
		 *            the message to set
		 */
		public void setMessage(String failureMessage) {
			this.message = failureMessage;
		}

	}

	public static class JsonErrorResponse<RESPONSE_ENTITY extends JSONable>
			extends JsonFailResponse<RESPONSE_ENTITY> {

		/**
		 * @param errorMessage
		 */
		public JsonErrorResponse(String errorMessage) {
			super(errorMessage);
		}

	}

}
