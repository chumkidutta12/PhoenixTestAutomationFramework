package com.api.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UsersCredentials;

public class ExcelReaderUtil {
	private ExcelReaderUtil()
	{
		
	}
	
	public static Iterator<UsersCredentials> loadTestData() 
	{
		//Apache POI OOXML Lib
		InputStream is= Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("testData/PhoenixTestData.xlsx");
		
	XSSFWorkbook myWorkBook = null;
	try {
		myWorkBook = new XSSFWorkbook(is);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//Focus on the Sheet
	XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");
	
	
	//Read the excel file -----> store in the ArrayList<UserCredentials>
	
	// I want to know the indexes for the username and password in our sheet!
	
	XSSFRow headerRows = mySheet.getRow(0);	//HeaderRows
	
	int userNameIndex=-1;
	int passwordIndex=-1;
	for(Cell cell: headerRows)
	{	
		if(cell.getStringCellValue().trim().equalsIgnoreCase("username"))
		{
			userNameIndex= cell.getColumnIndex();
		}
		if(cell.getStringCellValue().trim().equalsIgnoreCase("password"))
		{
			passwordIndex= cell.getColumnIndex();
		}
	}
		System.out.println(userNameIndex+"   "+passwordIndex);
		
		int lastRowIndex=mySheet.getLastRowNum();
		XSSFRow rowData;
		UsersCredentials userCredentials;
		ArrayList<UsersCredentials> userList= new ArrayList<UsersCredentials>();
		for(int rowIndex=1; rowIndex<=lastRowIndex; rowIndex++)
		{
			rowData=mySheet.getRow(rowIndex);
		 userCredentials= new UsersCredentials(rowData.getCell(userNameIndex).toString(), rowData.getCell(passwordIndex).toString());
			userList.add(userCredentials);
		}
		 System.out.println(userList.iterator());
		 return userList.iterator();

	}

}