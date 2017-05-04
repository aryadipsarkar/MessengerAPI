package org.arya.messenger.exception;

public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID= 1L;

    /**
     * Mapping exceptions (DataNotFoundException.class) to response (ErrorMessage.class))
     * This is done by a class called Exception Mapper
     * @param message
     */

    public DataNotFoundException(String message){
        super(message);
    }
}
