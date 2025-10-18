package com.api.tests;
import org.hamcrest.Matchers;
import org.testng.annotations.*;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization", AuthTokenProvider.getToken(Role.FD))	//raw header
		.and()
		.contentType("")
		.log().all()
		.when()
		.post("master")//end point	//default content type application/url form encoded
		.then()
		.log().all()
		.statusCode(200)
		.time(Matchers.lessThan(1000L))
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
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		//.header("Authorization", AuthTokenProvider.getToken(Role.FD))	//raw header
		.and()
		.contentType("")
		.log().all()
		.when()
		.post("master")//end point	//default content type application/url form encoded
		.then()
		.log().all()
		.statusCode(401);
	}
}
