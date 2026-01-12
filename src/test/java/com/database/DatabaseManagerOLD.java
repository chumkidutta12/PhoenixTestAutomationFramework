package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManagerOLD //throws IOException
{
    
    	private static final String DB_URL= ConfigManager.getProperty("DB_URL");
    	private static final String DB_USERNAME= ConfigManager.getProperty("DB_USERNAME");
    	private static final String DB_PASSWORD= ConfigManager.getProperty("DB_PASSWORD");
    	
    	private volatile static Connection conn;	//Any updates that happens to this conn variable, all threads will be aware of it!!
    
		
        private DatabaseManagerOLD()
        {
        	
        }   


    public synchronized static void createConnection() throws SQLException {
        if(conn== null) {	//Only and only for first connection Request
    	synchronized (DriverManager.class)
    	{
    		if(conn==null) {
    			conn= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    	        System.out.println(conn);
    		}

    		}
    	}
    }

    }
    

