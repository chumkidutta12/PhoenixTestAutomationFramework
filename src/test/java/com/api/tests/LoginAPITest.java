package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UsersCredentials;
import com.api.utils.ConfigManagerOld;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		//ConfigManager configManager= new ConfigManager();
		UsersCredentials usercredentials = new UsersCredentials("iamfd", "password");
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON) // header
				.and().accept(ContentType.JSON)
				.and()
				.body(usercredentials)
				.log().uri()
				.log().method()
				.log().headers().log().body()
				.log().all()
				.when()
				.post("login").then()
				.log().all().statusCode(200)
				// .time(Matchers.lessThan(1500L));
				.time(lessThan(1500L))
				.and()
				.body("message", equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
