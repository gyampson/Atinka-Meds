package ui;

public class CancelInputException extends RuntimeException {
    public CancelInputException() {
        super("User cancelled the input.");
    }
}

