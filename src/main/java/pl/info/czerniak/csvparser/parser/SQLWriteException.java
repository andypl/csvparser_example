package pl.info.czerniak.csvparser.parser;

class SQLWriteException extends Exception{
    private static final long serialVersionUID = 1L;

    SQLWriteException(long rowId) {
        super("Failed write '" + rowId + "' record to database");
    }
}
