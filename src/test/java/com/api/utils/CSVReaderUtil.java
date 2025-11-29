package com.api.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
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
	{ //No one cane create object of CSVReaderUtil Outsid the class
		// Singleton Class Constructos are private
	}
	
	
	public static <T> Iterator<T> loadCSV(String pathOfCSVFile, Class<T> bean) throws IOException, CsvException {
			//testData/LoginCreds.csv
			InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);	
			InputStreamReader isr= new InputStreamReader(is);
			CSVReader csvReader= new CSVReader(isr);		//csvReader Constructor 
			
			// Write the code to Map the CSV to POJO			
			//Class<UserBean> bean= UserBean.class;
			
			CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
					.withType(bean)
					.withIgnoreEmptyLine(true)
					.build();
			
			List<T> list=csvToBean.parse();
			//System.out.println(list);
			return list.iterator();

		}
	
	
	
	
/*public static Iterator<UserBean> loadCSV(String pathOfCSVFile) throws IOException, CsvException {
			
	//testData/LoginCreds.csv
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);	
		InputStreamReader isr= new InputStreamReader(is);
		CSVReader csvReader= new CSVReader(isr);		//csvReader Constructor 
		
		// Write the code to Map the CSV to POJO
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		List<UserBean> userList=csvToBean.parse();
		System.out.println(userList);
		return userList.iterator();

	}
	
	*/
}
