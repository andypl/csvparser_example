package pl.info.czerniak.csvparser.parser3;

import org.apache.commons.csv.CSVFormat;

import java.nio.charset.Charset;
import java.util.List;

public class CSVFileSpec {

    private List<String> headerList;
    private long colCount;
    private Charset charset;
    private CSVFormat csvFormat;

    private CSVFileSpec (){}

    public static final class Builder{
        private List<String> headerList;
        private long colCount;
        private Charset charset;
        private CSVFormat csvFormat;

        public static Builder builder(){
            return new Builder();
        }

        public Builder headerNames(List<String> headerList){
            this.headerList = headerList;
            return this;
        }

        public Builder columnCount(long colCount){
            this.colCount = colCount;
            return this;
        }

        public Builder charset(Charset charset){
            this.charset = charset;
            return  this;
        }

        public Builder csvFormat(CSVFormat csvFormat){
            this.csvFormat = csvFormat;
            return this;
        }

        public CSVFileSpec build(){
            if(headerList == null || colCount == 0 || charset == null || csvFormat == null){
                throw new IllegalStateException("Missing argument");
            }
            CSVFileSpec csvFileSpec = new CSVFileSpec();
            csvFileSpec.headerList = this.headerList;
            csvFileSpec.colCount = this.colCount;
            csvFileSpec.charset = this.charset;
            csvFileSpec.csvFormat = this.csvFormat;
            return new CSVFileSpec();
        }
    }
}
