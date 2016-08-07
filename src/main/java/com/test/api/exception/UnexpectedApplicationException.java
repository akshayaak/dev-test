package com.test.api.exception;

/**
 * The application level generic exception class
 * 
 * @author Akshay
 *
 */
public class UnexpectedApplicationException extends Exception {

    private static final long serialVersionUID = -101111111111111L;

    /**
     * Create a new instance.
     *
     * @param message
     *            a message describing what went wrong
     */
    public UnexpectedApplicationException(String message) {
        super(message);
    }

}
