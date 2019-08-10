package restassured.common;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

//
//   using https://jsonplaceholder.typicode.com
//

    @BeforeClass
    public void setup() {
        RestAssured.baseURI  = "https://jsonplaceholder.typicode.com";
    }

}
