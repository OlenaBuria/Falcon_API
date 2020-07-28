package tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;

import com.pearson.common.framework.shared.report.LoggerUtil;
import com.pearson.common.framework.api.core.Header;
import com.pearson.common.framework.api.core.Method;
import com.pearson.common.framework.api.core.RESTServiceBase;
import com.pearson.common.framework.api.core.RESTWrapper;
import com.pearson.common.framework.api.core.Responses;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import pojos.GetEnrollment;
import pojos.PiValidAuthResponse;

public class SampleLoginTest extends BaseClass {
	
	@Test
	public void piLoginTest1() throws Exception{
		
		    JSONObject jsonObject = new JSONObject();
		    jsonObject.put("userName", username);
		    jsonObject.put("password", password);
		    
		    Response response = RESTServiceBase.postCallWithJsonBodyParam(jsonObject, url);
		    response.prettyPrint();
		    xauthorization = response.jsonPath().get("data");
		    LoggerUtil.log("x-Authorization : " +xauthorization);
		  
		   
		    Assert.assertEquals(response.getStatusCode(), 201);
		    
	} 
	
	@Test
	public void PiTokenTest2() throws Exception{
		
		JSONObject jsonObject = new JSONObject();
	    jsonObject.put("userName", username);
	    jsonObject.put("password", password);
	    
	    Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
	    		.header("content-type", "application/json").header("x-apikey", "ba09cf335cebdf1c48f565422ab3fcab")
	    		.body(jsonObject.toString()).when().post(url);
	    response.prettyPrint();
	    xauthorization = response.jsonPath().get("data");
	    LoggerUtil.log("x-Authorization : " +xauthorization);
	  
	   
	    Assert.assertEquals(response.getStatusCode(), 201);
		
	}
	/**
	 * Sample test with POST call using legacy RESTServiceBase class available in Integrated Framework (with POJO class)
	 * 
	 * @throws Exception 
	 */
	
	@Test
	public void piLoginTest3() throws Exception{
		
		    JSONObject jsonObject = new JSONObject();
		    jsonObject.put("userName", username);
		    jsonObject.put("password", password);
		    
		    Response response = RESTServiceBase.postCallWithJsonBodyParam(jsonObject, url);
		    LoggerUtil.log(response.asString());
		    PiValidAuthResponse res = mapper.readValue(response.asString(), PiValidAuthResponse.class);
		    Assert.assertEquals(res.getStatus(), "success");
		    Assert.assertNotNull(res.getData());
		    Assert.assertEquals(response.getStatusCode(), 201);
		    
	} 
	
	/**
	 * Sample test with POST call using new RESTWrapper
	 * 
	 * @throws Exception 
	 */

	@Test
	public void piLoginTest4() throws Exception{
		
		HashMap<String, Header> authHeader = new HashMap<String, Header>();		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", username);
		jsonObject.put("password", password);
		
		Responses responses = RESTWrapper.doRequest(authHeader, ContentType.JSON, url, jsonObject.toString(), Method.POST);
		System.out.println(responses.getBody().toString());
		
		
		PiValidAuthResponse res = mapper.readValue(responses.getBody().getBodyString(), PiValidAuthResponse.class);
	    Assert.assertEquals(res.getStatus(), "success");
	    Assert.assertNotNull(res.getData());
		    
	} 
	
	@Test
	public void PiValidAuthResponse() throws Exception{
		
		Response response = RESTServiceBase.getCall(url);
		PiValidAuthResponse res = mapper.readValue(response.getBody().asString(), PiValidAuthResponse.class);
		response.prettyPrint();
		
		LoggerUtil.log(res.getStatus());
		LoggerUtil.log(res.getData());
		
	}
	
}
