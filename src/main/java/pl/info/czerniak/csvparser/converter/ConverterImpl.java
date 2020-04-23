package pl.info.czerniak.csvparser.converter;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.postgresql.copy.CopyIn;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * This class will convert input file to fit table in database.
 * It take 1 and 3 column from csv file, add UUID randomally generated and save it to a database using COPY API.
 */
public class ConverterImpl implements Converter {

    private CSVParser csvParser;
    private CopyIn copyIn;
    private long convertedRows = 0;
    private long rows = 0;

    private ConverterImpl(){};

    public ConverterImpl(CSVParser csvParser, CopyIn copyIn) throws IOException {
        this.copyIn = copyIn;
        this.csvParser = csvParser;
    }

    @Override
    public void convert() throws SQLException {
        if(this.copyIn == null || this.csvParser == null) {
            throw new NullPointerException();
        }
        try{
            for(CSVRecord record : this.csvParser){
                if(rowFilter(record)){
                    this.copyIn.writeToCopy(columnFilter(record), 0, columnFilter(record).length);
                    convertedRows++;
                }
                rows = record.getRecordNumber();
            }
            copyIn.endCopy();
        }finally {
            if(copyIn.isActive()){
                copyIn.endCopy();
            }
        }
    }

    @Override
    public byte[] columnFilter(CSVRecord record) {
        return (record.get(1) + "," + record.get(3) + "," + UUID.randomUUID().toString()+"\n").getBytes();
    }

    @Override
    public boolean rowFilter(CSVRecord record) {
        return record.getRecordNumber() % 2 == 0;
    }

    public long getConvertedRows() {
        return convertedRows;
    }

    public long getRows() {
        return rows;
    }
}
