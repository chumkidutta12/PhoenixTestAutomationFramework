package com.database;

import java.io.IOException;
import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException, IOException {
		for(int i=1;i<=1000; i++) {
		DatabaseManagerOLD.createConnection();
		DatabaseManagerOLD.createConnection();
	
		}
	}



}
