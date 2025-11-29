package com.api.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.api.request.model.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;
import com.opencsv.exceptions.CsvException;

public class Demo {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub		
		//System.out.println(System.getProperty("user.dir"));
		
		Iterator<CreateJobBean> iterator =CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
		ArrayList<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		while(iterator.hasNext())
		{
			//System.out.println(iterator.next());
			
			CreateJobBean c= iterator.next();
			CreateJobPayload payload= CreateJobBeanMapper.mapper(c);
			System.out.println(payload);
			payloadList.add(payload);

		}
		
	}

}
