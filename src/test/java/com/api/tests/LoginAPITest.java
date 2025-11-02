package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.ConfigManagerOld;
import com.api.utils.SpecUtil;
import com.api.request.model.UsersCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		//ConfigManager configManager= new ConfigManager();
		UsersCredentials usercredentials = new UsersCredentials("iamfd", "password");
		
		given()
		.spec(SpecUtil.requestSpec(usercredentials))
				.and()
				//.body(usercredentials)
				.when()
				.post("login").then()
				.spec(SpecUtil.responseSpec_OK())
				// .time(Matchers.lessThan(1500L));
				.and()
				.body("message", equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
