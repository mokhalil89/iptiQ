package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestApiClient {

    public Response get(String url) {
        return given().spec(new RequestSpecBuilder().build()).get(url);
    }

    public Response post(String url, Object payload) {
        return given().spec(new RequestSpecBuilder().setContentType(ContentType.JSON).build()).body(payload).post(url);
    }

    public Response put(String url, Object payload) {
        return given().spec(new RequestSpecBuilder().setContentType(ContentType.JSON).build()).body(payload).put(url);
    }

    public Response delete(String url) {
        return given().spec(new RequestSpecBuilder().build()).delete(url);
    }
}