package dev.tuxjsql.hikaricp.cp;

import com.google.auto.service.AutoService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.tuxjsql.core.TuxJSQL;
import dev.tuxjsql.core.connection.ConnectionProvider;
import dev.tuxjsql.core.connection.ConnectionSettings;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The core to the HikariConnection Provider
 */
@AutoService(ConnectionProvider.class)
public class HikariConnectionProvider implements ConnectionProvider {
    private HikariDataSource dataSource;

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            TuxJSQL.getLogger().error("Unable to get connection", e);
        }
        return null;
    }

    @Override
    public void close() {
        dataSource.close();
    }

    @Override
    public void returnConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            TuxJSQL.getLogger().error("Unable to return connection", e);
        }
    }

    @Override
    public boolean isConnected() {
        return !dataSource.isClosed();
    }

    @Override
    public String name() {
        return "hikaricp-cp";
    }

    @Override
    public void setup(ConnectionSettings settings, Properties userSettings) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(settings.getDriver());
        config.setJdbcUrl(settings.getUrl());
        config.setUsername(userSettings.getProperty("user", ""));
        config.setPassword(userSettings.getProperty("password", ""));
        config.setIdleTimeout(Long.parseLong(userSettings.getProperty("idle.timeout", "30000")));
        config.setLeakDetectionThreshold(Long.parseLong(userSettings.getProperty("leak.detection", "30000")));
        config.setMaximumPoolSize(Integer.parseInt(userSettings.getProperty("pool.size", "5")));
        config.setPoolName(userSettings.getProperty(userSettings.getProperty("pool.name", "TuxJSQL")));
        dataSource = new HikariDataSource(config);
    }
}
