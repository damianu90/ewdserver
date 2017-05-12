/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duzieblo.ewdserver.db;

import com.duzieblo.ewdserver.config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Damian Uziębło
 */
public class DbHelper implements AutoCloseable{
    private Connection connect;
    private String dbHost;
    private String dbConnectAdres;
    private String dbName;
    private String user;
    private String password;

    public DbHelper(String dbHost, String dbName, String user, String password) {
        this.dbHost = dbHost;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        
        this.dbConnectAdres = Config.DRIVER_MYSQL_CONNECT_PREFIX + dbHost;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }
    
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private void connection() throws ClassNotFoundException, SQLException {
        Class.forName(Config.DRIVER_MYSQL);
        connect = DriverManager.getConnection(dbConnectAdres + dbName, user, password);
    }
    
    public Connection openConnection() throws ClassNotFoundException, SQLException {
        connection();
        return this.connect;
    }
    
    public Connection getConnection() {
        return this.connect;
    }

    @Override
    public void close() throws Exception {
        this.connect.close();
    }
    
    
}
