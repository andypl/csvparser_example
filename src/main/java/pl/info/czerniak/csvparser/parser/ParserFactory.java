package pl.info.czerniak.csvparser.parser;

import javax.naming.directory.NoSuchAttributeException;

public abstract class ParserFactory {
    public abstract CSVFileParserFactory getCSVFileParserFactory();

    public static ParserFactory getInstance(FactoryType type) throws NoSuchAttributeException {
        switch (type){
            case FILE_FACTORY:
                return new FileParserFactory();
            default: throw new NoSuchAttributeException("Wrong factory type.");
        }
    }
}
