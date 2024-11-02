import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReqResTests {

    @Test
    public void loginTest() {

        given().
                log().all()
                .contentType(ContentType.JSON)
                .body("""
                        
                                                           \s
                                                                  {
                                                                    "email": "eve.holt@reqres.in",
                                                                      "password": "cityslicka"
                                                                  }
                        
                        
                        """).post("https://reqres.in/api/login")
                .then().log().all();
    }

    @Test
    public void loginTestExitoso() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("""
                        {
                                                                                            "email": "eve.holt@reqres.in",
                                                                                              "password": "joel"
                                                                                          }
                        """).post("https://reqres.in/api/login")
        .then().log().all();
    }




}



