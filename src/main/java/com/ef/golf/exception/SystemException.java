package com.ef.golf.exception;



public class SystemException extends Exception {
	private static final long serialVersionUID = 5203101980583056700L;

	private int retCode = 0;
	private String message;

	public SystemException(int code) {
		this.retCode = code;
	}

	public SystemException(String message) {
		this.message = message;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
