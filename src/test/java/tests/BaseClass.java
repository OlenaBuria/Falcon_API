package tests;

import org.codehaus.jackson.map.ObjectMapper;

import com.pearson.common.framework.api.core.EnvParameters;
import com.pearson.common.framework.api.core.RESTServiceBase;
import com.pearson.common.framework.shared.datareader.TestData;


public class BaseClass extends RESTServiceBase {

	protected static String Env = EnvParameters.ENV;
	
	//# STG Project 1: Sample Login Test
	protected static String url = TestData.getData(Env + "_Url");
	protected static String username = TestData.getData(Env + "_Username");
	protected static String password = TestData.getData(Env + "_Password");	
	
	
	//# STG Project 2: Sample Demo API Collection
	protected static String url_piAuthRoute = TestData.getData(Env + "_PiAuthRoute_Url");
	protected static String username_API = TestData.getData(Env + "_Username_API");
	protected static String password_API = TestData.getData(Env + "_Password_API");
	
	protected static String crystallakeEnv_url = TestData.getData(Env + "_Crystallake_Env");	
	protected static String get_enrollment_url = TestData.getData(Env + "_GetEnrollment_Url");	
	protected static String get_tasklists_url = TestData.getData(Env + "_Get_TaskLists_Url");
	protected static String get_submission_url = TestData.getData(Env + "_Get_Submission_Url");
	protected static String modify_submission_url = TestData.getData(Env + "_Modify_Submission_Url");	
	
	// Global Collection Variables
	protected static String xauthorization = null;
	protected static String courseID = null;
	protected static String projectSubmissionId = null;
	protected static Integer getSequenceNumber =  null;
	protected static String documentIdUpdated = null;
	
	protected ObjectMapper mapper = new ObjectMapper();
}
