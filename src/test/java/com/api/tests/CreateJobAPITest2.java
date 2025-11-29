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
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest2 {
	CreateJobPayload createJobPayload;
	
	private final static String COUNTRY= "INDIA";

	@BeforeMethod(description="Creating createjob api request payload")
	public void setup()
	{
		// Create Fake CreateJobAPI Request Payload
					//I want to create a Fake Customer Object!!
					Faker faker= new Faker(new Locale("en-IND"));   //Help me create India specific fake data
					
					
					
					String fname= faker.name().firstName();
					String lname= faker.name().lastName();
					String mobileNumber= faker.numerify("70########");
					String alternetMobileNumber= faker.numerify("70########");
					String customerEmailAddress= faker.internet().emailAddress();
					String altCustomerEmailAddress= faker.internet().emailAddress();
					
					Customer customer= new Customer(fname, lname, mobileNumber, alternetMobileNumber, customerEmailAddress, altCustomerEmailAddress);
					System.out.println(customer);
					
					
					String flatNumber= faker.numerify("###");
					String apartmentName= faker.address().streetName();	
					String streetName= faker.address().streetName();			
					String landmark= faker.address().streetName();
					String area= faker.address().streetName();
					String pincode= faker.numerify("#####");
					
					String state= faker.address().state();			
					CustomerAddress customerAddress= new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pincode, COUNTRY, state);
					System.out.println(customerAddress);			
					
					//Customer Product fake Object
					String dop= DateTimeUtil.getTimeWithDaysAgo(10);
					String imeiSerialNumber= faker.numerify("##############");
					String popUrl= faker.internet().url();
					
					CustomerProduct customerProduct= new CustomerProduct(dop,imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popUrl, 1, 1);
					System.out.println(customerProduct);
					
					String fakeRemark= faker.lorem().sentence(5);
					//I want to generate random number between 1 to 27
					Random random = new Random();
					int problemId= random.nextInt(26)+1;
					
					Problems problems= new Problems(problemId, fakeRemark);			
					System.out.println(problems);
					
					List<Problems> problemList= new ArrayList<Problems>();
					problemList.add(problems);
					
					createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);


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
