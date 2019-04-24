package com.wallet.walletapi.dto;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
public class CommonResponse<T> {
	private String status , message;
	private T data;
	
	public  CommonResponse() {
		this.status = "101";
		this.message = "Success";
	}

	public CommonResponse(String code, String description) {
		this.status = code;
		this.message = description;
	}

	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
