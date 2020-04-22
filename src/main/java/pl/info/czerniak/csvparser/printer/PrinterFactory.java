package pl.info.czerniak.csvparser.printer;

import pl.info.czerniak.csvparser.old_parsers.parser.FactoryType;

import javax.naming.directory.NoSuchAttributeException;

public abstract class PrinterFactory {
    public abstract void print();

    public static FilePrinterFactory getInstance(FactoryType type) throws NoSuchAttributeException {
        switch (type){
            case FILE_FACTORY:
                return new FilePrinterFactory();
            default: throw new NoSuchAttributeException("Wrong factory type.");
        }
    }
}
