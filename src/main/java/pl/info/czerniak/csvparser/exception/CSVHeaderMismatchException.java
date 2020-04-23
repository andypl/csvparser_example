package pl.info.czerniak.csvparser.exception;

public class CSVHeaderMismatchException extends Exception {
    private static final long serialVersionUID = 1L;

    public CSVHeaderMismatchException(String message) {
        super(message);
    }

    public CSVHeaderMismatchException(){
        super();
    }
}
