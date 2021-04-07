package model;

public class IncorrectInputException extends Exception {

    public IncorrectInputException() {
        super("Your Goal mass cannot exceed or be the same as your current mass.");
    }
}
