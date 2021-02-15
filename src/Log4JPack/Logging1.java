package Log4JPack;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Logging1
{
		
	public static RequestSpecification httpRequest = null;
	public static Response response = null;

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI="https://reqres.in";	
		httpRequest = RestAssured.given();
	}
	@Test	
	public void testLogging1()
	{
		System.out.println("***************** Start - testLogging1 *********************");
		
		httpRequest.given().get("/api/users?page=2").
		then()
		//.log().headers();
		.log().body();
		//.log().cookies();
		 // .log().all();
		
		System.out.println("***************** End - testLogging1 *********************");
	}
				
	//Logs only in case of errors		
		@Test		
		public void testLogging2()
		{
			System.out.println("***************** Start - testLogging2 *********************");
			httpRequest.given().
			get("/api").
			then().			
			log().ifError();
			System.out.println("***************** End - testLogging2 *********************");
		}
				
	//conditional logging
		@Test		
		public void testLogging3()
		{
			System.out.println("***************** Start - testLogging3 *********************");
			httpRequest.given().			
			get("/api/v1/employee/1").
			then().
			log().ifStatusCodeIsEqualTo(200);
			System.out.println("***************** End - testLogging3 *********************");
		}
	}