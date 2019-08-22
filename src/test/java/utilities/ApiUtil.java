package utilities;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class ApiUtil {

    public static String path;

    public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }

    public static void setBasePath(String basePath){
        RestAssured.basePath = basePath;
    }

    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    public static void setContentType (ContentType Type){
        given().contentType(Type);
    }

    public static String createSearchQueryPath(String series, String driverType, String param, String paramValue) {
        path ="/" +series + "/" + driverType+"?"+param+ "=" +paramValue ;
        return path;
    }
    public static Response getResponse(String path) { return get(path); }

    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }
}
