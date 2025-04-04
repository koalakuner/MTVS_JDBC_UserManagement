package com.ohgiraffers.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {
    private static final HikariDataSource dataSource;

    static {
        try {
            Properties props = new Properties();
            props.load(JDBCConnection.class.getClassLoader().getResourceAsStream("config.properties"));
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.username"));
            config.setPassword(props.getProperty("db.password"));

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            config.setMaxLifetime(1800000);
            config.setConnectionTimeout(60000);
            dataSource = new HikariDataSource(config);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection()  throws SQLException {
    return dataSource.getConnection();
    }
    public static void close(){
        if(dataSource != null){
            dataSource.close();
        }
    }
    public static void printConnection() {
        HikariPoolMXBean poolMXBean = dataSource.getHikariPoolMXBean();
        System.out.println("[hikariCp 커넥션 풀 상태 ]");
        System.out.println("총 커넥션 수 (total Connections) : " + poolMXBean.getTotalConnections());
        System.out.println("활성 커넥션 수 (Active Connections) : " + poolMXBean.getActiveConnections());
        System.out.println("유휴 커넥션 수 (IDLE Connections) : " + poolMXBean.getIdleConnections());
        System.out.println("대기 중인 커넥션 요청 수 (Pending Threads) : " + poolMXBean.getThreadsAwaitingConnection());
    }
}

