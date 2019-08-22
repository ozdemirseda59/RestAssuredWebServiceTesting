package utilities;


import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HelperMethods {

    public static void checkStatusIs200 (Response res) { assertEquals("Status Check Failed!", 200, res.getStatusCode()); }

    public static ArrayList getDriversIdList (JsonPath jp) {
        ArrayList driverIdList =jp.get("MRData.DriverTable.Drivers.driverId");
        return driverIdList;
    }

    public static ArrayList getDriversList (JsonPath jp) {
        ArrayList driversList =jp.get("MRData.DriverTable.Drivers");
        return driversList;
    }

    //Find Duplicate Drivers
    public static boolean findDuplicateDrivers (List<Integer> driverIdList) {
        for (int i=0; i< driverIdList.size(); i++) {
            if(Collections.frequency(driverIdList, driverIdList.get(i)) > 1){
                System.out.println("This driver id is duplicated: " + driverIdList.get(i));
                return false;
            }
        }
        return true;
    }
}
