package pl.info.czerniak.csvparser.parser;

public class DatabaseConfig {
    private String jdbcURL;
    private String driverClassName;
    private String username;
    private String password;

    private DatabaseConfig(){}

    public String getJdbcURL() {
        return jdbcURL;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder{
        private String jdbcURL;
        private String driverClassName;
        private String username;
        private String password;

       public static Builder builder(){
            return new Builder();
        }

        public Builder jdbcURL(String s){
            this.jdbcURL = s;
            return this;
        }

        public Builder driverClassName(String s){
            this.driverClassName = s;
            return this;
        }

        public Builder username(String s){
            this.username = s;
            return this;
        }

        public Builder password(String s){
            this.password = s;
            return this;
        }

        public DatabaseConfig build(){
            if(jdbcURL == null || driverClassName == null || username == null || password == null){
                throw new IllegalStateException("Missing argument");
            }
            DatabaseConfig databaseConfig = new DatabaseConfig();
            databaseConfig.jdbcURL = this.jdbcURL;
            databaseConfig.driverClassName = this.driverClassName;
            databaseConfig.username = this.username;
            databaseConfig.password = this.password;
            return databaseConfig;
        }
    }
}
