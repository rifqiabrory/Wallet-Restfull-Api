package com.wallet.walletapi.exception;

/**
 * @RIFQIABRORY web developer
 * enigma batch II
 */
@SuppressWarnings("serial")
public class BaseCommonException extends Exception {

    protected String code;
    protected String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
