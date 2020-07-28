package tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.common.framework.api.core.RESTServiceBase;
import com.pearson.common.framework.shared.report.LoggerUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import pojos.ChildGetTaskLists;
import pojos.GetEnrollment;
import pojos.GetSubmissions;
import pojos.GetTaskLists;

import java.sql.Timestamp;
import java.util.List;

public class SampleDemoAPICollection extends BaseClass {
	
	// Constant variables
	protected static String bearer = "Bearer ";
	
	@Test
	public void sample_piLogin() throws Exception{
		
		JSONObject jsonObject = new JSONObject();
	    jsonObject.put("userName", username_API);
	    jsonObject.put("password", password_API);
	    
	    Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
	    		.header("content-type", "application/json").header("accept-encoding", "deflate")
	    		.body(jsonObject.toString()).when().post(url_piAuthRoute);
	    response.prettyPrint();
	    xauthorization = response.jsonPath().get("data");
	    String piTokenStatus = response.jsonPath().get("status");
	    LoggerUtil.log("Response token : " + xauthorization);
	    LoggerUtil.log("Response status : " + piTokenStatus);
	   
	    Assert.assertEquals(response.getStatusCode(), 201);
		    
	} 

	@Test(dependsOnMethods = {"sample_piLogin"})
	public void getCourseId() throws Exception {
		
		// Sample 1
		Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("content-type", "application/json").header("Authorization",bearer+xauthorization)
				.when().get(get_enrollment_url);
		
		// Sample 2
		//Response response = RESTServiceBase.getCall(get_enrollment_url);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		
		GetEnrollment res = mapper.readValue(response.asString(), GetEnrollment.class);
		
		// Sample 1
		courseID = res.getCourseId();		
		LoggerUtil.log(courseID);
		
		// Sample 2
		//courseID = response.jsonPath().get("courseId");
		//LoggerUtil.log(courseID);
		
		LoggerUtil.log(res.getCourseTitle());
		LoggerUtil.log(res.getEnrollmentStatus());
		LoggerUtil.log(res.getLearnerId());
		LoggerUtil.log(res.getMentorId());
		LoggerUtil.log(res.getProductId());
		LoggerUtil.log(res.getProductTitle());
		LoggerUtil.log(res.getStatus());
		
	}
	@Test(dependsOnMethods={"sample_piLogin","getCourseId"})

	public void getTaskList() throws Exception {
		
		Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
	             .header("content-type","application/json").header("Authorization",bearer+xauthorization)
	             .when().get(get_tasklists_url.replace("{STG_Crystallake_Env}", crystallakeEnv_url)
	            		 .replace("{STG_CourseId}", courseID));
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();	
		
		// Sample 1
		String a=response.jsonPath().getList("children[0]").toString();
		LoggerUtil.log(response.jsonPath().getList("children[0]").toString());
		
		// Sample 2
		 
		GetTaskLists[] pojores = mapper.readValue(response.asString(), GetTaskLists[].class);
		List<ChildGetTaskLists> children = pojores[0].getChildren();
		Assert.assertNotNull(children);
		ChildGetTaskLists child2assetId = children.get(2);
		projectSubmissionId = child2assetId.getAssetId();
		LoggerUtil.log("projectSubmissionId: " + projectSubmissionId);
		System.out.println(projectSubmissionId);
		
		// Sample 3

		GetTaskLists[] tasklist = response.getBody().as(GetTaskLists[].class);
		List<ChildGetTaskLists> children1 = tasklist[0].getChildren();
		projectSubmissionId =children1.get(2).getAssetId();
		LoggerUtil.log("projectSubmissionId: " + projectSubmissionId);
		System.out.println(projectSubmissionId);
    }

	@Test(dependsOnMethods= {"getTaskList"})
	public void getLearnerSubmission() throws Exception {
		
		Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
	             .header("content-type","application/json").header("Authorization",bearer+xauthorization)
	             .when().get(get_submission_url.replace("{STG_Crystallake_Env}", crystallakeEnv_url)
	            		 .replace("{STG_CourseId}", courseID)
	            		 .replace("{STG_projectSubmissionId}", projectSubmissionId));
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		
		// Sample 1
		GetSubmissions pojores = mapper.readValue(response.asString(), GetSubmissions.class);
		Assert.assertTrue(pojores.getChildren().size() > 0); // check that children is not EMPTY
		getSequenceNumber = pojores.getChildren().get(0).getSequence();
		LoggerUtil.log("sequence: " + getSequenceNumber);
		
		// Sample 2 (review code)
		
		//String a=response.jsonPath().getList("children[0]").toString();
		//LoggerUtil.log(response.jsonPath().getList("children[0]").toString());
		//LoggerUtil.log("sequence number: " + getSequenceNumber);
		//System.out.println(a);
		
		// Sample 3 (review code)
		
		//GetSubmissions[] getsubmissionsresponse = response.getBody().as(GetSubmissions[].class);
		//List<ChildGetSubmissions> children1 = getsubmissionsresponse[0].getChildren();
		//System.out.println(getsubmissionsresponse);
		//getSequenceNumber = children1.get(0).getSequence().toString();
		//LoggerUtil.log("sequence number: " + getSequenceNumber);
		//System.out.println(getSequenceNumber);
		
	} 
	
	@Test(dependsOnMethods = {"getLearnerSubmission"})
	public void modifySubmission() throws Exception{
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long timeMilliSeconds = timestamp.getTime();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sequence", getSequenceNumber);
		jsonObject.put("type", "application/pdf");
		jsonObject.put("documentId", "QApdf" + Long.toString(timeMilliSeconds));
		jsonObject.put("documentName", "QApdf" + Long.toString(timeMilliSeconds) + ".pdf");
		jsonObject.put("documentSize", "1000");
		
		Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
	             .header("content-type","application/json").header("Authorization",bearer+xauthorization)
	             .body(jsonObject).when()
	             .put(modify_submission_url.replace("{STG_Crystallake_Env}", crystallakeEnv_url)
	            		 .replace("{STG_CourseId}", courseID)
	            		 .replace("{STG_projectSubmissionId}", projectSubmissionId)
	            		 .replace("{STG_Sequence}", Integer.toString(getSequenceNumber)));
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
			
		Assert.assertTrue(response.getStatusCode() == 200);
		
		documentIdUpdated = response.jsonPath().get("documentId");
		LoggerUtil.log("documentidUpdated: " + documentIdUpdated);
				
	}

	
	@Test(dependsOnMethods = {"modifySubmission"})
	public void checkUpdatedProjectSubmission() throws Exception{
			
		Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
	             .header("content-type","application/json").header("Authorization",bearer+xauthorization)
	             .when().get(get_submission_url.replace("{STG_Crystallake_Env}", crystallakeEnv_url)
	            		 .replace("{STG_CourseId}", courseID)
	            		 .replace("{STG_projectSubmissionId}", projectSubmissionId));
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		
		Assert.assertTrue(response.getStatusCode() == 200);

		GetSubmissions pojores = mapper.readValue(response.asString(), GetSubmissions.class);
		Assert.assertTrue(pojores.getChildren().size() > 0); // check that children is not EMPTY
		getSequenceNumber = pojores.getChildren().get(0).getSequence();
		LoggerUtil.log("sequence: " + getSequenceNumber);
			
	}
	
	@Test(dependsOnMethods = {"checkUpdatedProjectSubmission"})
	public void deleteSubmission() throws Exception{
		
		Response response = given().log().all().contentType(ContentType.JSON).accept(ContentType.JSON)
	             .header("content-type","application/json").header("Authorization",bearer+xauthorization)
	             .when()
	             .delete(modify_submission_url.replace("{STG_Crystallake_Env}", crystallakeEnv_url)
	            		 .replace("{STG_CourseId}", courseID)
	            		 .replace("{STG_projectSubmissionId}", projectSubmissionId)
	            		 .replace("{STG_Sequence}", Integer.toString(getSequenceNumber)));
		response.prettyPrint();
		
		Assert.assertTrue(response.getStatusCode() == 200);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
