package com.bol.kalah.exception;

public class UnsupportedKalahOperationException extends KalahException {
    public UnsupportedKalahOperationException(String message) {
        super("Wrong operation, you can't do that now. " + message);
    }
}
