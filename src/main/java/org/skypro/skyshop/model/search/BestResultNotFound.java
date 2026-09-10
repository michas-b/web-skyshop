package org.skypro.skyshop.model.search;

public class BestResultNotFound extends Exception {

    public BestResultNotFound(String message) {
        super(message);
    }

    public BestResultNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
