package jdbc;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

@Getter
@Setter
public class CustomDataSource implements DataSource {
//    static OracleConnectionPoolDataSource ocpds = null;
//    private DataSource ods;
    private static volatile CustomDataSource instance;
//    private final static Properties properties = new Properties();
    private final String driver;
    private final String url;
    private final String name;
    private final String password;
//    private static DataSource datasource;

//        private CustomDataSource(){
//        this.driver = properties.getProperty("postgres.driver");
//        this.url = properties.getProperty("url");
//        this.name = properties.getProperty("name");
//        this.password = properties.getProperty("password");
//    }

    private CustomDataSource(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.name = name;
        this.password = password;
//        try {
//            Class.forName(driver);//2
//        } catch (ClassNotFoundException e) {
//            //throw new ExceptionInInitializerError(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//        try {
//            ocpds = new OracleConnectionPoolDataSource();
//            ocpds.setURL(jdbcUrl);
//            ocpds.setUser(user);
//            ocpds.setPassword(password);
//
//            //Create a pooled connection
//            pc_1 = ocpds.getPooledConnection();
//
//            //Open a Connection and a Statement object
//            Connection conn_1 = pc_1.getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

//        url = "jdbc:mysql://localhost:3306/catalog";
//        Properties prop = new Properties();
//        prop.put("user",name);
//        prop.put("password",password);
//
//            Connection connection = null;
//            try {
//                connection = DriverManager.getConnection(url,prop);
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }




//        try {
//            OracleDataSource ods = new OracleDataSource();
//
//            ods.setUser(name);
//            ods.setPassword(password);
//            ods.setDriverType(driver);
//            ods.setDatabaseName("myfirstdb");
//            ods.setServerName("localhost");
//            ods.setPortNumber(5432);

//            Connection connection = ods.getConnection();
//            System.out.println("Connection successful!!!");

//        } catch (SQLException se) {
//            se.printStackTrace();
//        }



    }
    public static CustomDataSource getInstance() {
        if(instance == null) {
            Properties properties = new Properties();
            try(InputStream input = CustomDataSource.class.getClassLoader().
                    getResourceAsStream("app.properties") ){
                properties.load(input);
                instance = new CustomDataSource(properties.getProperty("postgres.driver"),
                        properties.getProperty("postgres.url"),
                        properties.getProperty("postgres.password"),
                        properties.getProperty("postgres.name"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new CustomConnector().getConnection(url,name,password);
//        Properties prop = new Properties();
//        prop.put("user",name);
//        prop.put("password",password);
//
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(url,prop);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    //public class DataSource {
    //
    //    private BasicDataSource basicDataSource;
    //
    //    private static DataSource ourInstance = new DataSource();
    //
    //    private DataSource() {
    //        try {
    //
    //            basicDataSource = new BasicDataSource();
    //
    //            basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    //
    //            basicDataSource.setUrl("jdbc:mysql://localhost:3306/database");
    //
    //            basicDataSource.setUsername("root");
    //
    //            basicDataSource.setPassword("");
    //
    //            basicDataSource.setInitialSize(10);
    //
    //            basicDataSource.setMaxWaitMillis(180000);
    //
    //            basicDataSource.setDefaultAutoCommit(true);
    //
    //            basicDataSource.setPoolPreparedStatements(true);
    //
    //            basicDataSource.setConnectionProperties("useUnicode=true;characterEncoding=utf8");
    //
    //            basicDataSource.setLogAbandoned(true);
    //            basicDataSource.setRemoveAbandonedTimeout(30);
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
    //
    //    public static DataSource getInstance() {
    //        return ourInstance;
    //    }
    //
    //    public BasicDataSource getDataSource() {
    //        return basicDataSource;
    //    }
    //
    //    public Connection getConnection() throws SQLException {
    //        return basicDataSource.getConnection();
    //    }
    //
    //}

}
