package manuall.config;

import javax.sql.DataSource;

import manuall.Main;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Properties;

public class JdbcConfig {

    public static DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        Properties properties = Main.getProperties();
        dataSource.setDriverClassName(properties.getProperty("driverClassName"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        return dataSource;
    }
}