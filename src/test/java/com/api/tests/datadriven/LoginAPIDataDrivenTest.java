package com.api.tests.datadriven;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.utils.ConfigManagerOld;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;
import com.api.request.model.UsersCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDataDrivenTest {
	
	UsersCredentials usercredentials;
	
	

	@Test(description="Verify if the UserCredentials api response is shown correctly", groups= {"api", "regression", "smoke"}, 
						dataProviderClass= com.dataproviders.DataProviderUtils.class,
						dataProvider="LoginAPIDataProvider" )
	public void loginAPITest(UserBean userbean) throws IOException{
		//ConfigManager configManager= new ConfigManager();
		
		
		given()
		.spec(SpecUtil.requestSpec(userbean))
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
