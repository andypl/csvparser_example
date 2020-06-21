package pl.info.czerniak.csvparser.parser;

class SQLEndCopyException extends Exception{
    private static final long serialVersionUID = 1L;

    SQLEndCopyException() {
        super("Failed to end copy operation!");
    }
}