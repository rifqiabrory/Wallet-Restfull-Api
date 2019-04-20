package com.wallet.walletapi.exception;

@SuppressWarnings("serial")
public class UserException extends BaseCommonException{

	public UserException(String code,String description) {
		this.code = code;
		this.description = description;
	}

}
