package com.wallet.walletapi.exception;

/**
 *   @RIFQIABRORY
 *   web developer
 *   enigma batch II
 * */
@SuppressWarnings("serial")
public class NotFoundException extends BaseCommonException{

	public NotFoundException(String code,String description) {
		this.code = code;
		this.description = description;
	}
}
