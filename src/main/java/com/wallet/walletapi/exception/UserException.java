package com.wallet.walletapi.exception;

/**
 * @RIFQIABRORY web developer
 * enigma batch II
 */

@SuppressWarnings("serial")
public class UserException extends BaseCommonException {

    public UserException(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
