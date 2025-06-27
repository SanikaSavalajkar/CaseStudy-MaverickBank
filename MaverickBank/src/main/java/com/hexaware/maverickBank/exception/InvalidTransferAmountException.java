package com.hexaware.maverickBank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid transfer amount")
public class InvalidTransferAmountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidTransferAmountException(String message) {
        super(message);
    }
    public InvalidTransferAmountException() {
        super("Invalid transfer amount");
    }
}