package Log4JPack;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReqresRequests extends TestBaseReqres{
	
	RequestSpecification httpRequest = null;
	Response response = null;
	@BeforeMethod
	public void BeforeMethod() {
		//declare the baseURL 
		RestAssured.baseURI = "https://reqres.in";
		//This specifices this is HTTP Protocol Specification
		httpRequest = RestAssured.given();		
	}
		
	@Test(priority=0)
	public void GetListOfUsersRequest() {
		{
			response = httpRequest.get("/api/users?page=2");
			JsonPath js = response.jsonPath();
			
			int arraycount = js.getInt("data.size()");
			for (int i =0 ; i<arraycount;i++) {
				System.out.println("Email["+i+"] -" +js.get("data["+i+"].email"));
				System.out.println("FirstName["+i+"] -" +js.get("data["+i+"].first_name"));
				System.out.println("Lastname["+i+"] -" +js.get("data["+i+"].last_name"));
				System.out.println("Avatar["+i+"] -" +js.get("data["+i+"].avatar"));
				System.out.println("ID["+i+"] -" +js.get("data["+i+"].id"));
								
			}
			String[] expectedemail = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in","george.edwards@reqres.in", "rachel.howell@reqres.in"};
			String[] expectfirstname = {"Michael", "Lindsay", "Tobias", "Byron","George", "Rachel"};
			String[] expectlastname ={"Lawson", "Ferguson", "Funke", "Fields","Edwards", "Howell"};
			String[] expectavatar = {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
			int[] expectID = {7, 8, 9,10,11,12};
			
			String emailresponse = null;
			String Firstnameresponse = null;
			String Lastnameresponse = null;
		    String avatarresponse = null;
		    int IDresponse;
			
			for (int j=0; j<arraycount;j++)
			{
			emailresponse = js.get("data["+j+"].email");
			Firstnameresponse = js.get("data["+j+"].first_name");
			Lastnameresponse = js.get("data["+j+"].last_name");
			avatarresponse = js.get("data["+j+"].avatar");
			IDresponse = js.get("data["+j+"].id");
		
				Assert.assertEquals(emailresponse,expectedemail[j], "assert pass");	
				System.out.println("checked correct " + emailresponse);
				Assert.assertEquals(Firstnameresponse,expectfirstname[j], "assert pass");	
				System.out.println("checked correct " + Firstnameresponse);
				Assert.assertEquals(Lastnameresponse,expectlastname[j], "assert pass");	
				System.out.println("checked correct " + Lastnameresponse);
				Assert.assertEquals(avatarresponse,expectavatar[j], "assert pass");	
				System.out.println("checked correct " + avatarresponse);
				Assert.assertEquals(IDresponse,expectID[j], "assert pass");	
				System.out.println("checked correct " + IDresponse);
		
			}		

		}
		logger.info("****Inside checkStatusCode List users*******");
		int statusCode=response.getStatusCode();
		logger.info("StatusCode Get list of users ==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		logger.info("********Inside checkStatusLine List Users*********");
		String statusLine=response.getStatusLine();
		logger.info("StatusLine Get list of users ==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		logger.info("********Inside checkContentType List Users*********");
		String contentType=response.header("Content-Type");
		logger.info("Content type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	
	}
	
	@Test(priority=1)
	public void CreateUserRequest() {
		JSONObject requestparams = new JSONObject();
		requestparams.put("name", "morpheus");
		requestparams.put("job", "leader");
		//httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestparams.toJSONString());
		
		Response response= httpRequest.post("/api/users");
		int StatusCode = response.getStatusCode();
		String StatusLine = response.getStatusLine();
		String ResponseBody =response.body().asString();
		System.out.println("Status Code = " + StatusCode);
		System.out.println("Status Line = " + StatusLine);
		System.out.println("Response Body = " + ResponseBody);	
		logger.info("****Inside checkStatusCode Create user*******");
		int statusCode=response.getStatusCode();
		logger.info("StatusCode Createuser ==>"+statusCode);
		Assert.assertEquals(statusCode, 201);
	
		logger.info("********Inside checkStatusLine Create User*********");
		String statusLine=response.getStatusLine();
		logger.info("StatusLine Create user ==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
		
		logger.info("********Inside checkContentType Cerate User*********");
		String contentType=response.header("Content-Type");
		logger.info("Content type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		
}
	@Test(priority=2)
	public void UpdateUserRequest() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "morpheus");
		requestParams.put("job", "zion resident");
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.queryParam("page",2).put("/api/users");
		
		
		int statusCode = response.getStatusCode();
		String statusLine = response.getStatusLine();
		String responseBody = response.getBody().asString();
		System.out.println("Response Status Code : " + statusCode);
		System.out.println("Response Status Line : " + statusLine);
		System.out.println("Response Body : " + responseBody);
		logger.info("****Inside checkStatusCode Update User*******");
		int statusCode1=response.getStatusCode();
		logger.info("StatusCode Update ==>"+statusCode1);
		Assert.assertEquals(statusCode1, 200);
		
		logger.info("********Inside checkStatusLine Update User*********");
		String statusLine1=response.getStatusLine();
		logger.info("StatusLine Update  ==>"+statusLine1);
		Assert.assertEquals(statusLine1, "HTTP/1.1 200 OK");
		
		logger.info("********Inside checkContentType Update User*********");
		String contentType=response.header("Content-Type");
		logger.info("Content type is ==>"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
	}
	
	@Test(priority=3)
	public void DeleteUserRequest() {
		
		response = httpRequest.queryParam("page",2).delete("/api/users");
		int statusCode = response.getStatusCode();
		String statusLine = response.getStatusLine();
		String responseBody = response.getBody().asString();
		System.out.println("Response Status Code : " + statusCode);
		System.out.println("Response Status Line : " + statusLine);
		System.out.println("Response Body : " + responseBody);
		logger.info("****Inside checkStatusCode Delete*******");
		int statusCode1=response.getStatusCode();
		logger.info("StatusCode Delete ==>"+statusCode1);
		Assert.assertEquals(statusCode1, 204);
		
		logger.info("********Inside checkStatusLine Delete*********");
		String statusLine1=response.getStatusLine();
		logger.info("StatusLine Delete ==>"+statusLine1);
		Assert.assertEquals(statusLine1, "HTTP/1.1 204 No Content");
		logger.info("********Inside checkContentType Delete*********");
		
	}
	
}
	
	