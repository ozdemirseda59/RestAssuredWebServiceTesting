package ApiTests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.HelperMethods;
import utilities.ApiUtil;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleTest extends BaseTest {

    @Test
    public void T01_StatusCodeTest() {
        ApiUtil.createSearchQueryPath("f1","drivers.json","limit","1");
        res = ApiUtil.getResponse(ApiUtil.path);
        jp = ApiUtil.getJsonPath(res);
        HelperMethods.checkStatusIs200(res);
    }

    @Test
    public void T02_GetDrivers() {
        ApiUtil.createSearchQueryPath("f1","drivers.json","limit","1");
        res = ApiUtil.getResponse(ApiUtil.path);
        jp = ApiUtil.getJsonPath(res);
        assertEquals("F1 Drivers Size is not equal to 1", 1, HelperMethods.getDriversIdList(jp).size());
        System.out.println(HelperMethods.getDriversList(jp));
    }

    @Test
    public void T03_GetVerifyDriversOptions() {
        ApiUtil.createSearchQueryPath("f1","drivers.json","limit","1");
        res = ApiUtil.getResponse(ApiUtil.path);
        jp = ApiUtil.getJsonPath(res);
        assertEquals("F1 Drivers is not equal to 1", 1, HelperMethods.getDriversIdList(jp).size());
        assertEquals("abate", jp.get("MRData.DriverTable.Drivers.driverId[0]"));
        assertEquals("Carlo", jp.get("MRData.DriverTable.Drivers.givenName[0]"));
        assertEquals("Abate", jp.get("MRData.DriverTable.Drivers.familyName[0]"));
    }

    @Test
    public void T04_GetDriversOptions() {
        ApiUtil.createSearchQueryPath("f1","drivers.json","limit","15");
        res = ApiUtil.getResponse(ApiUtil.path);
        jp = ApiUtil.getJsonPath(res);
        assertEquals("F1  Drivers Size is not equal to 15", 15, HelperMethods.getDriversList(jp).size());
        assertTrue("Duplicate drivers exist!", HelperMethods.findDuplicateDrivers(HelperMethods.getDriversIdList(jp)));
    }

    @Test
    public void T05_DuplicateVideoVerification() {
        ApiUtil.createSearchQueryPath("f1","drivers.json","limit","10");
        res = ApiUtil.getResponse(ApiUtil.path);
        jp = ApiUtil.getJsonPath(res);
        assertEquals("F1  Drivers Size is not equal to 10", 10, HelperMethods.getDriversList(jp).size());
        printDriverIdGivenNameFamilyName(jp);
    }

    @Test
    public void T06_Post()
    {
        RestAssured.baseURI  = "http://ergast.com/";
        Response res = given()
                .contentType("application/json").
                        body("{\"driverId\":\"obo\",\"url\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/obo\",\"givenName\":\"oboN\",\"givenName\":\"oboNo\",\"dateOfBirth\":\"1932-07-10\"," +
                                "\"nationality\":\"Italian\"}").
                        when().
                        post("");

        String body = res.getBody().asString();
        System.out.println(body);

    }

    private void printDriverIdGivenNameFamilyName (JsonPath jp) {
        for(int i=0; i < HelperMethods.getDriversIdList(jp).size(); i++ ) {
            System.out.println("DriverId: " + jp.get("MRData.DriverTable.Drivers.driverId[" + i + "]"));
            System.out.println("GivenName: " + jp.get("MRData.DriverTable.Drivers.givenName[" + i + "]"));
            System.out.println("FamilyName: " + jp.get("MRData.DriverTable.Drivers.familyName[" + i + "]"));
            System.out.print("\n");
        }
    }


}




