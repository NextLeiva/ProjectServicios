import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqResTests {

    /*antes y despues de un test el hook preecondiciones para los test

     */
    @BeforeAll
    public static void setUp() {
        //se ejecuta solo una vez
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/";
        //nos ayuda filtrar el request y response de login
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        // tipo de respuesta que esperamos el servidor
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void loginTest() {

        given()
                .body("""
                        
                                                           \s
                                                                  {
                                                                      "email": "eve.holt@reqres.in",
                                                                      "password": "cityslicka"
                                                                  }
                        
                        
                        """).post("login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue());
    }

    @Test
    public void GetloginTest() {
        given()
                .get("users/2")
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                //equal to para evaluar el numeral y valida cadenas
                .body("data.id", equalTo(2))
                //machers contenga una porcion de todo el string
                .body("data.email", containsString("janet"))
                //machers que contenga el texto pero que ignore las mayusculas
                .body("data.email",containsStringIgnoringCase("JANET"))
                //matchers valida que sea el mismo texto
                .body("data.email",equalTo("janet.weaver@reqres.in"))
                //matchers que sea mayor a 1
                .body("data.id",greaterThan(1))
                //matchers comparar igual a 2;
                .body("data.id",greaterThanOrEqualTo(2));


    }


    @Test
    public void DeleteUserTest() {
        given()
        .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(3));
    }


}



