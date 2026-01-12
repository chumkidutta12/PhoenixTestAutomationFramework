package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager //throws IOException
{
    
    	private static final String DB_URL= ConfigManager.getProperty("DB_URL");
    	private static final String DB_USERNAME= ConfigManager.getProperty("DB_USERNAME");
    	private static final String DB_PASSWORD= ConfigManager.getProperty("DB_PASSWORD");
    	private static final int MAXIMUM_POOL_SIZE=Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
    	private static final int MINIMUM_IDLE_COUNT=Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
    	private static final int CONNECTION_TIMEOUT_IN_SECS=Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
    	private static final int IDLE_TIMEOUT_SECS=Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_SECS"));
    	private static final int MAX_LIFE_TIME_IN_MINS=Integer.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
    	private static final String HIKARI_CP_POOL_NAME= ConfigManager.getProperty("HIKARI_CP_POOL_NAME");
    	private static HikariConfig hikariConfig;
    	private volatile static HikariDataSource hikariDataSource;

    	private  static Connection conn;
    
		
        private DatabaseManager()
        {
        	
        }   


        public  static void initializePool() //throws SQLException, NumberFormatException, IOException {
        { if(hikariDataSource== null) {	//First check all parallel threads will enter
        	synchronized (DatabaseManager.class)
        	{
        		if(hikariDataSource==null) {	//
        			HikariConfig hikariConfg = new HikariConfig();
        			hikariConfg.setJdbcUrl(DB_URL);
        			hikariConfg.setUsername(DB_USERNAME);
        			hikariConfg.setPassword(DB_PASSWORD);
        			hikariConfg.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
        			hikariConfg.setMinimumIdle(MINIMUM_IDLE_COUNT);
        			hikariConfg.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS*1000);     //10secs
        			hikariConfg.setIdleTimeout(IDLE_TIMEOUT_SECS);

        			hikariConfg.setMaxLifetime(MAX_LIFE_TIME_IN_MINS*60*1000);		//30 mins 30*60*1000
        			hikariConfg.setPoolName(HIKARI_CP_POOL_NAME);
        			
        			hikariDataSource=new HikariDataSource(hikariConfg);
        		}

        		}
        	}
        }
        
        public static Connection getConnection() throws SQLException
        {
        	Connection connection=null;
        	if(hikariDataSource==null)
        	{
        		initializePool();		//Automatic Initialization of HikariDataSource s
        	}
        	else if(hikariDataSource.isClosed())
        	{
        		throw new SQLException("hIKARI DATA SOURCE IS CLOSED");
        	}        	
        		connection= hikariDataSource.getConnection();
                
        	return connection;
        }
    
}
