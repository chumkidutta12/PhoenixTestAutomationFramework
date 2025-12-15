package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dataproviders.api.bean.CreateJobBean;

public class ExcelReaderUtil3 {
	public static void main(String[] args) {
		
		Iterator<CreateJobBean> iterator= ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx", "CreateJobTestData",CreateJobBean.class);
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	
	}
	
	
	

}
