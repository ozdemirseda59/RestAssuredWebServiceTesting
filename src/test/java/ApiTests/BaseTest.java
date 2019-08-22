package ApiTests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ApiUtil;
import utilities.HelperMethods;

public class BaseTest {
    public Response res = null; //Response
    public JsonPath jp = null; //JsonPath

    HelperMethods helperMethods =new HelperMethods();

    @BeforeClass
    public void setup (){
        //Test Setup
        ApiUtil.setBaseURI("http://ergast.com/"); //Setup Base URI
        ApiUtil.setBasePath("api"); //Setup Base Path
        ApiUtil.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest (){
        //Reset Values
        ApiUtil.resetBaseURI();
        ApiUtil.resetBasePath();
    }
}
