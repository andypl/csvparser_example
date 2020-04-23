package pl.info.czerniak.csvparser.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import pl.info.czerniak.csvparser.parser.CSVFileParserImpl;
import pl.info.czerniak.csvparser.parser.CSVFileSpec;

import java.nio.charset.Charset;
import java.util.List;

/**
 * This class is something like a wrapper to a CSVFileParserImpl.
 * It hide details about it and simply return an CSVParser object for future use.
 */
public class ParserService {

    private String fileName;
    private List<String> headerList;
    private CSVFileSpec csvFileSpec;


    public ParserService(String fileName, List<String> headerList){
        this.fileName = fileName;
        this.headerList = headerList;

        csvFileSpec = CSVFileSpec.Builder.builder()
                .columnCount(10)
                .charset(Charset.defaultCharset())
                .csvFormat(CSVFormat.DEFAULT)
                .headerNames(headerList)
                .build();
    }

    public CSVParser getCSVParser(){
        CSVFileParserImpl csvFileParser = new CSVFileParserImpl(fileName, csvFileSpec);
        return csvFileParser.getCsvParser();
    }

}
