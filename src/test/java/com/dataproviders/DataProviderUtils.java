package com.dataproviders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import com.opencsv.exceptions.CsvException;

public class DataProviderUtils {
	@DataProvider(name= "LoginAPIDataProvider", parallel= true)
 public static Iterator<UserBean> loginAPIDataProvider() throws IOException, CsvException
 {
		return CSVReaderUtil.loadCSV("testData/LoginCreads.csv", UserBean.class);
	 
 }
	
	// Data Provider needs to return something!!
	//[]
	//[][]
	//Iterator
	
	
	@DataProvider(name="CreateJobAPIDataProvider" , parallel= true)
	public static Iterator<CreateJobPayload> createJobDataProvider() throws IOException, CsvException
	{
		Iterator<CreateJobBean> createJobBeanIterator= CSVReaderUtil.loadCSV("testData/CreateJobData.csv", 
																				CreateJobBean.class);
		
		List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();

		CreateJobBean  tempBean;
		CreateJobPayload tempPayload;
		while(createJobBeanIterator.hasNext())
		{
			tempBean= createJobBeanIterator.next();
			tempPayload= CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}
		
		return payloadList.iterator();
	}
}
