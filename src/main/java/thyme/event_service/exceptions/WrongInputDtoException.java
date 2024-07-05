package thyme.event_service.exceptions;

public class WrongInputDtoException extends RuntimeException {
    public WrongInputDtoException(String message) {
        super(message);
    }
}
