package pl.info.czerniak.csvparser.reader;

import java.io.IOException;

public class EmptyFileException extends IOException {
    private static final long serialVersionUID = 1L;

    EmptyFileException(String s) {
        super(s);
    }
}
