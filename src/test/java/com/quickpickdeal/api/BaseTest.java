package com.quickpickdeal.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public final String BASE_URL = "https://www.quickpickdeal.com";
    public final String PATH = "/api";

    public RequestSpecification getBase() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(PATH)
                .addHeader("Content-Type", "application/json")
                .build();
    }


    public String getBearerToken() {
        return given()
                .spec(getBase())
//                .log().uri()
                .when()
                .get("/Auth/GetBearerToken")
                .then()
//                .log().body()
                .extract().jsonPath().getString("Data.Token");
    }

    public RequestSpecification getSpecificationWithBearerToken(){
        return new RequestSpecBuilder()
                .addRequestSpecification(getBase())
                .addHeader("Authorization", "Bearer "+getBearerToken())
                .build();
    }
}
