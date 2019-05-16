package com.wallet.walletapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wallet.walletapi.dto.CommonResponse;

/**
 * @RIFQIABRORY web developer
 * enigma batch II
 */
@ControllerAdvice
public class UserExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<CommonResponse> catchUserException(UserException e) {
        LOGGER.info("UserException");
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());

        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getDescription()), HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<CommonResponse> catchNotFoundException(NotFoundException e) {
        LOGGER.info("NotFoundException");
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());

        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getDescription()), HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponse> catchException(Exception e) {
        LOGGER.info("Exception Found");
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());

        return new ResponseEntity<CommonResponse>(new CommonResponse("500", "Exception found"), HttpStatus.OK);
    }
	
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse> catchHttpNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOGGER.info("RequestMethodNotSupported");
        LOGGER.error(e.getMessage());
        LOGGER.warn(e.getMessage());
        LOGGER.info(e.getMessage());
        LOGGER.debug(e.getMessage());

        return new ResponseEntity<CommonResponse>(new CommonResponse("405", "RequestMethodNotSupported"), HttpStatus.OK);
    }
}
