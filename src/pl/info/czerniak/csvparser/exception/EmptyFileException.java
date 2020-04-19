package pl.info.czerniak.csvparser.exception;

import java.io.IOException;

public class EmptyFileException extends IOException {
    private static final long serialVersionUID = 1L;

    public EmptyFileException(String s) {
        super(s);
    }
}
