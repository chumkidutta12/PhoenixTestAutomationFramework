package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CountAPITest {

	@Test(description="Verify if the count api is giving correct response.", groups= {"api", "regression", "smoke"})
	public void verifyCountAPIResponse() throws IOException
	{
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message",Matchers.equalTo("Success"))
		.time(Matchers.lessThan(1000L))
		.body("data",Matchers.notNullValue())
		.body("data.size()", Matchers.equalTo(3))
		//.body("data.count",Matchers.everyItem(Matchers.everyItem(Matchers.greaterThanOrEqualTo(0))))
		.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())) )
		.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
	}
	
	@Test(description="Verify if the count api is giving correct status code for invalid token.", groups= {"api","negative", "regression", "smoke"})
	public void countAPITest_MissingAuthToken() throws IOException
	{
		given()
		.spec(SpecUtil.requestSpec())
		//.header("Authorization", AuthTokenProvider.getToken(Role.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
