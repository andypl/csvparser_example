package pl.info.czerniak.csvparser.printer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import pl.info.czerniak.csvparser.validators.FileValidator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class CSVFilePrinterFactoryImpl implements CSVFilePrinterFactory {
    @Override
    public void printToFile(CSVParser csvParser, CSVFormat csvFormat, String fileName) throws IOException {
        print(csvParser, csvFormat, fileName);
    }

/*
    Print 3 cols:
        1 <- 2 from origin
        2 <- 4 from origin
        3 <- random generated UUID
        Each row number must euqal to: col number MOD 2 = 0
 */
    private void print(CSVParser csvParser, CSVFormat csvFormat, String fileName) throws IOException {
        File file = FileValidator.validateOutputFile(fileName);
        CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(file), csvFormat.withHeader("HeaderCol1", "HeaderCol2", "HeaderCol3"));
        for(CSVRecord record : csvParser){
            if(record.getRecordNumber() % 2 == 0) {
                csvPrinter.printRecord(record.get(1), record.get(3), UUID.randomUUID().toString());
            }
        }
        csvPrinter.close();

    }
}
