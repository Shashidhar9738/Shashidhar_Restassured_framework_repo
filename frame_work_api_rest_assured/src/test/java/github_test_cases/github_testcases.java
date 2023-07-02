package github_test_cases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import baseclass.basefile;
import io.restassured.response.Response;
import resource.pojoclass;
import utility.Authentication;
import utility.base_url;
import utility.common_file;
import utility.payloadconvertor;
public class github_testcases  {
	public static String endpoint=base_url.getbaseuri("/user/repos");
	public static String bearer_token=Authentication.getbearertoken();
	public static Response response;
	public static ObjectMapper objectmapper;
	
	
	@Test(enabled=false)
	public void createRepository() throws IOException
	{
		String request_payload=payloadconvertor.generatepayload("CreateRepo.json");
		System.out.println(request_payload);
		response=basefile.PostRequest(endpoint, request_payload, bearer_token);
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		//Assert.assertEquals(CommonResponse.getReponse(request_payload, "name"), CommonResponse.getReponse(responseBody, "name"));
		Assert.assertEquals(common_file.get_status_code(response), 201);
	}
	@Test
	public void CreateRepoDynamic() throws IOException
	{
		//String request_payload=PayLoadConvertor.generate_Paload("CreateRepo.json");
		//System.out.println(request_payload);
		//i do not have to give it since we will give it dynamically at runtime
		//create object for pojoclass
		pojoclass createpojo=new pojoclass();
		createpojo.setName("Shashidhar_Restassured_framework_repo");
		createpojo.setDecsription("this_is_created_by_me_on_this_sunday");
		//i have to map this at class level
		objectmapper=new ObjectMapper();
		String data=objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(createpojo);
		response=basefile.PostRequest(endpoint, data, bearer_token);
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		Assert.assertEquals(common_file.getResponse(data, "name"), common_file.getResponse(responseBody, "name"));
		Assert.assertEquals(common_file.get_status_code(response), 201);
	}


	
	
	
}
