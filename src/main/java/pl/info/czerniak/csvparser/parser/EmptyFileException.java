package pl.info.czerniak.csvparser.parser;

import java.io.IOException;

class EmptyFileException extends IOException {
    private static final long serialVersionUID = 1L;

    EmptyFileException(String s) {
        super(s);
    }
}
