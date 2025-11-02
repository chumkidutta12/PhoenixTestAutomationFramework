package com.api.tests;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() throws IOException
	{
		//Creating the CreateJobPayload Object
		Customer customer=new Customer("Sabrina", "Lesch", "999-236-1523", "", "Adriel33@hotmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("101", "Sunita Residency", "Hallur Road", "Sarjapur", "harlur","560102", "India", "Karnataka");
		
	   CustomerProduct customer_product= new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10),"06647782745374","06647782745374","06647782745374",DateTimeUtil.getTimeWithDaysAgo(10),3,3); 
	   Problems problems=new Problems(2, "Testing");
	   List<Problems> problemList=new ArrayList<Problems>();
	   problemList.add(problems);
	   
	   CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 2, customer, customerAddress, customer_product, problemList);
		 
			
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",Matchers.equalTo("Job created successfully. "))
		//.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.mst_platform_id", Matchers.equalTo(2))
		.body("data.job_number",Matchers.startsWith("JOB_"));
	}
}
