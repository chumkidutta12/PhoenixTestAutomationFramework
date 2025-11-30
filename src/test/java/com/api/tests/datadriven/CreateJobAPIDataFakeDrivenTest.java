package com.api.tests.datadriven;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
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

public class CreateJobAPIDataFakeDrivenTest {
	
	/*
	@BeforeMethod(description="Creating createjob api request payload")
	public void setup()
	{
		Customer customer=new Customer("Sabrina", "Lesch", "999-236-1523", "", "Adriel33@hotmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("101", "Sunita Residency", "Hallur Road", "Sarjapur", "harlur","560102", "India", "Karnataka");
		
	   CustomerProduct customer_product= new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10),"77647782745374","77647782745374","77647782745374",DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),Model.NEXUS_2_BLUE.getCode()); 
	   Problems problems=new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Testing");
	   List<Problems> problemList=new ArrayList<Problems>();
	   problemList.add(problems);
	   
	    createJobPayload= new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(),Warranty_Status.IN_WARRANTY.getCode(),OEM.GOOGLE.getCode(), customer, customerAddress, customer_product, problemList);
		 
	}
	*/
	
	
	@Test(description="Verify if the create job api is able to create Inwarranty job.", groups= {"api", "regression", "datadriven"},
			dataProviderClass= com.dataproviders.DataProviderUtils.class, 
			dataProvider = "CreateJobAPIFakerDataProvider")
	
	
	public void createJobAPITest(CreateJobPayload createJobPayload) throws IOException
	{
		//Creating the CreateJobPayload Object
		
			
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
