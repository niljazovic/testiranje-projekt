package hr.ferit.framework.api.core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class ApiBaseTest {

    @BeforeClass
    public void apiSetup() {
        RestAssured.baseURI = System.getProperty(
                "apiBaseUrl",
                "https://jsonplaceholder.typicode.com"
        );
    }
}
