package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

//import com.api.utils.ConfigManagerOld;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static org.hamcrest.Matchers.*;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;

import java.io.IOException;

import io.restassured.module.jsv.JsonSchemaValidator;
public class UserDetailsAPITest {
	
	@Test(description="Verify if the Userdetails api response is shown correctly.", groups= {"api", "regression", "smoke"})
	public void userDetailsAPITest() throws IOException
	{	
		//ConfigManager configManager= new ConfigManager();	
		//Header authHeader= new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3NjAxNjU5Mzh9.8YniXVk_MA2VCYDGwbLfZM6EvAFQKU9vLBpiCNQ_GYc");
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
