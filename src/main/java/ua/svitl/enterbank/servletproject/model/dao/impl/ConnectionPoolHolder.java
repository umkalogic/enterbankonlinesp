package ua.svitl.enterbank.servletproject.model.dao.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger LOG = LogManager.getLogger(ConnectionPoolHolder.class);

    private static final HikariConfig config;
    private static final HikariDataSource ds;

    static {
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mytestbankdb?serverTimezone=UTC&useLegacyDatetimeCode=false" +
                "&useSSL=true&useUnicode=yes&characterEncoding=UTF-8");
        config.setUsername("sveta");
        config.setPassword("sveta");
        config.setAutoCommit(true);
        config.setMaximumPoolSize(12);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds = new HikariDataSource(config);
    }

    private ConnectionPoolHolder() {
    }

    public static DataSource getDataSource() {
        LOG.trace("Get data source ==> {} ", ds);
        return ds;
    }

}
