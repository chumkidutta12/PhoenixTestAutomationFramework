package com.api.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class CSVReaderUtil {
	
	/*
	 * *   Constructor is private
	 * *   static-  static methods! Job:Help me Read the CSV file and Map it a Bean
	 */
	
	private CSVReaderUtil()
	{ 
		
	}
	
public static void loadCSV(String pathOf) throws IOException, CsvException {
		
		
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");	
		InputStreamReader isr= new InputStreamReader(is);
		CSVReader csvReader= new CSVReader(isr);		//csvReader Constructor 
		
		// Write the code to Map the CSV to POJO
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader).withType(UserBean.class)
				.withIgnoreEmptyLine(true).build();
		
		List<UserBean> userList=csvToBean.parse();
		System.out.println(userList);

	}
}
