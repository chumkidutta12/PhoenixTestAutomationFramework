package com.api.tests;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestwithFakeData {
	CreateJobPayload createJobPayload;
	
	private final static String COUNTRY= "INDIA";

	@BeforeMethod(description="Creating createjob api request payload")
	public void setup()
	{
					
					createJobPayload = FakerDataGenerator.generateFakeCreateJobData();

	}
	
	
	@Test(description="Verify if the create job api is able to create Inwarranty job.", groups= {"api", "regression", "smoke"})
	public void createJobAPITest() throws IOException
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
