package g3.model.exceptions;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
