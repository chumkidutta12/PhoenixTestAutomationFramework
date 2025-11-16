package com.dataproviders;

import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;
import com.opencsv.exceptions.CsvException;

public class DataProviderUtils {
	@DataProvider(name= "LoginAPIDataProvider")
 public static Iterator<UserBean> loginAPIDataProvider() throws IOException, CsvException
 {
		return CSVReaderUtil.loadCSV("testData/LoginCreads.csv");
	 
 }
	
	// Data Provider needs to return something!!
	//[]
	//[][]
	//Iterator
}
