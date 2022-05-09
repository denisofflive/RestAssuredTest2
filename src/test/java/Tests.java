import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class Tests {

    @Test
    public  void testCreate() {
         /*
        request: https://reqres.in/api/users
        {
    "name": "morpheus",
    "job": "leader"
}
        response:
        {
    "name": "morpheus",
    "job": "leader",
    "id": "647",
    "createdAt": "2022-05-09T17:59:55.133Z"
}
         */
        StoreTest storeTest = new StoreTest
                ("morpheus","leader") ;

        given().baseUri("https://reqres.in/api/users").
                basePath("/rest-api").
                contentType(JSON).
                body(storeTest).
                log().all().

                when().post("stores").

                then().assertThat().
                statusCode(201).
                body("name", Matchers.equalTo("morpheus")).
                body("job", Matchers.equalTo("leader")).
                log().all();

    }
}
