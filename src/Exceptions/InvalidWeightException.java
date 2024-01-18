package Exceptions;

public class InvalidWeightException extends RuntimeException{
    InvalidWeightException(String message) {
        super(message);
    }
}
