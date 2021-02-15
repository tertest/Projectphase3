package Log4JPack;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OpenWeatherRequest extends TestBase {
	RequestSpecification httpRequest = null;
	Response response = null;
	@BeforeMethod
	public void BeforeMethod() {
		//declare the baseURL 
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/box";
		//This specifices this is HTTP Protocol Specification
		
		
		httpRequest = RestAssured.given();		
	}
	@Test
	public void GetRequestSomeCityWeather() {
		logger.info("********");
		response = httpRequest.request(Method.GET,"/city?bbox=12,32,15,37,10&appid=2c2ca1cfacc5b9e7a12f04ac9c53080a");
		//response = httpRequest.get("/api/users/2");
		int StatusCode = response.getStatusCode();
		String StatusLine = response.getStatusLine();
		String ResponseBody =response.body().asString();
		System.out.println("Status Code = " + StatusCode);
		System.out.println("Status Line = " + StatusLine);
		System.out.println("Response Body = " + ResponseBody);	
		logger.info("*******Inside checkResponseBody*******");
		String responseBody=response.getBody().asString();
		logger.info("Response Body ==> "+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	@Test
	void checkStatusCode() {
		logger.info("****Inside checkStatusCode*******");
		int statusCode=response.getStatusCode();
		logger.info("StatusCode ==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}	
	@Test
	void checkStatusLine() {
		logger.info("********Inside checkStatusLine*********");
		String statusLine=response.getStatusLine();
		logger.info("StatusLine ==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	void checkContentType() {
		logger.info("********Inside checkContentType*********");
		String contentType=response.header("Content-Type");
		logger.info("Content type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	}





