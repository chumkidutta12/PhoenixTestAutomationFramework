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

public class LoginAPIExcelDataDrivenTest {
	
	//UsersCredentials usercredentials;
	
	

	@Test(description="Verify if the login api  is working for FD user", groups= {"api", "regression", "smoke", "datadriven"}, 
						dataProviderClass= com.dataproviders.DataProviderUtils.class,
						dataProvider="LoginAPIExcelDataProvider" )
	public void loginAPITest(UserBean userBean) throws IOException{
		//ConfigManager configManager= new ConfigManager();
		
		
		given()
		.spec(SpecUtil.requestSpec(userBean))
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
