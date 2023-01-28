package testCases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ApiTest {
	//Test the output of API by parsing response
@Test
public void festivalsAPI()
{
	RestAssured.baseURI="https://eacp.energyaustralia.com.au/";
	String response=given().when().get("codingtest/api/v1/festivals").then()
			.assertThat().statusCode(200).extract().response().asString();
	JsonPath js=new JsonPath(response);
	
	//count total festivals
	int festivalCount=js.getInt("festivals.size()");
	System.out.println(festivalCount);
	
	//print first festival name
	String name=js.getString("festivals[0].name");
	System.out.println(name);
	//print count of bands under first festival
	for(int i=0;i<=festivalCount;i++)
	{
	int bandCount=js.getInt("festivals["+i+"].bands.size())");
	System.out.println(bandCount);
	}
}
}
