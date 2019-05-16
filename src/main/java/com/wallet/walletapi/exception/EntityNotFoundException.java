package com.wallet.walletapi.exception;

/**
 * @RIFQIABRORY web developer
 * enigma batch II
 */
public class EntityNotFoundException extends BaseCommonException {
    private static final long serialVersionUID = 318972360359255L;

    public EntityNotFoundException(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
