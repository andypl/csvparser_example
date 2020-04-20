package pl.info.czerniak.csvparser.printer;

public class FilePrinterFactory extends PrinterFactory{

    public CSVFilePrinterFactory getCSVFilePrinterFactory(){
        return new CSVFilePrinterFactoryImpl();
    }

    @Override
    public void print() {
    }
}
