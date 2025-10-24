package com.api.tests;
import org.hamcrest.Matchers;
import org.testng.annotations.*;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException
	{
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD))
		.when()
		.post("master")//end point	//default content type application/url form encoded
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"))
		.body("data", Matchers.notNullValue())
		.body("data",Matchers.hasKey("mst_oem")) // key is present or not 
		.body("data",Matchers.hasKey("mst_model"))
		.body("$", Matchers.hasKey("message"))
		.body("$", Matchers.hasKey("data"))
		.body("data.mst_oem.size()",Matchers.equalTo(2))
		.body("data.mst_model.size()",Matchers.greaterThan(0))
		.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
		.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	
	@Test
	public void invalidTokenMasterAPITest() throws IOException
	{
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post("master")//end point	//default content type application/url form encoded
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
