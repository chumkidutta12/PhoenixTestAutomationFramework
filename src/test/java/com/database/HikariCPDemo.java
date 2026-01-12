package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {

	public static void main(String[] args) throws IOException, SQLException {
		HikariConfig hikariConfg = new HikariConfig();
		hikariConfg.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariConfg.setUsername(ConfigManager.getProperty("DB_USERNAME"));
		hikariConfg.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariConfg.setMaximumPoolSize(10);
		hikariConfg.setMinimumIdle(2);
		hikariConfg.setConnectionTimeout(10000);     //10secs
		hikariConfg.setMaxLifetime(1800000);		 //30 mins
		hikariConfg.setPoolName("Phoenix Test Automation Framework Pool");
		
		
		HikariDataSource ds= new HikariDataSource(hikariConfg);
		Connection conn= ds.getConnection();
		
		System.out.println(conn);
		
		Statement statement= conn.createStatement();
		ResultSet rs= statement.executeQuery("SELECT first_name, last_name, mobile_number from tr_customer;");
		
		while(rs.next())
		{
			System.out.println(rs.getString("first_name")+ "  "+rs.getString("last_name")+ "   "+ rs.getString("mobile_number"));
		}
		
		ds.close();
		
	}

}
