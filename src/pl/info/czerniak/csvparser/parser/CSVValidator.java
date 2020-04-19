package pl.info.czerniak.csvparser.parser;

import org.apache.commons.csv.CSVFormat;

import java.nio.charset.Charset;

public class CSVValidator {
    private Charset charset;
    private int colNumbers;
    boolean header;
    CSVFormat csvFormat;

    public CSVValidator() {
    }

    public CSVValidator(Charset charset, int colNumbers, boolean header, CSVFormat csvFormat) {
        this.charset = charset;
        this.colNumbers = colNumbers;
        this.header = header;
        this.csvFormat = csvFormat;
    }

    public CSVFormat getCsvFormat() {
        return csvFormat;
    }

    public void setCsvFormat(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public int getColNumbers() {
        return colNumbers;
    }

    public void setColNumbers(int colNumbers) {
        this.colNumbers = colNumbers;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }
}
