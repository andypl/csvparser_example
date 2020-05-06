package pl.info.czerniak.csvparser.printer;

import org.apache.commons.csv.CSVRecord;

import java.util.UUID;

/**
 * An implementation of Converter2 interface.
 * It will take only even rows and return 2, 3 column form
 * given csv file, and add column with auto generated UUID.
 */
public class ConverterImpl implements Converter {

    private CSVRecord record;
    private byte[] result;

    @Override
    public void convert() {
        if(isRowConvertable(this.record)){
            this.result = (record.get(1) + "," + record.get(3) + "," + UUID.randomUUID().toString()+"\n").getBytes();
        }
    }

    @Override
    public boolean isRowConvertable(CSVRecord record) {
        if(record == null){
            throw new NullPointerException();
        }else if(record.getRecordNumber() % 2 == 0){
            this.record = record;
            return true;
        }
        return false;
    }

    @Override
    public byte[] getResult() {
        return this.result;
    }
}
