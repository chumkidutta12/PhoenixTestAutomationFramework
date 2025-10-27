package com.api.tests;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() throws IOException
	{
		//Creating the CreateJobPayload Object
		Customer customer=new Customer("Sabrina", "Lesch", "999-236-1523", "", "Adriel33@hotmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("101", "Sunita Residency", "Hallur Road", "Sarjapur", "harlur","560102", "India", "Karnataka");
		
	   CustomerProduct customer_product= new CustomerProduct("2025-06-09T18:30:00.000Z","106645792741341","106645792741341","106645792741341","2025-06-09T18:30:00.000Z",3,3); 
	   Problems problems=new Problems(2, "Testing");
	   Problems[] problemsArray=new Problems[1];
	   problemsArray[0]=problems;
	   CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 2, customer, customerAddress, customer_product, problemsArray);
		 

		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());
	}
}
