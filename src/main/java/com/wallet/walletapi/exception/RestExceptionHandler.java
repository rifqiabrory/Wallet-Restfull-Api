package com.wallet.walletapi.exception;

import com.wallet.walletapi.dto.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @RIFQIABRORY web developer
 * enigma batch II
 */
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return new ResponseEntity<Object>(new CommonResponse<>("06", "General Error"), headers, status);
    }
}
