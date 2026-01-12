package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner_CheckingOfHikariDataBaseManager {

	public static void main(String[] args) throws SQLException, IOException {
		Connection conn= DatabaseManager.getConnection();
		System.out.println(conn);
	}



}
