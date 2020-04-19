package pl.info.czerniak.csvparser.parser;

public class FileParserFactory extends ParserFactory {
    @Override
    public CSVFileParserFactory getCSVFileParserFactory() {
        return new CSVFileParserFactoryImpl();
    }
}
