package pl.info.czerniak.csvparser.exception;

public class IncorrectColNumberFileException extends Exception {
    private static final long serialVersionUID = 1L;

    public IncorrectColNumberFileException(String s) {
        super(s);
    }
}
