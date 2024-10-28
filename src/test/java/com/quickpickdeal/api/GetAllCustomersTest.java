package com.quickpickdeal.api;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllCustomersTest extends BaseTest {
    @Test
    public void getAllCustomers() {
        given()
                .spec(getSpecificationWithBearerToken())
                .log().uri()
                .when()
                .get("/Customer/GetAllCustomers")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void getCustomerDetail() {

        int id = given()
                .spec(getSpecificationWithBearerToken())
                .log().uri()
                .when()
                .get("/Customer/GetAllCustomers")
                .then()
//                .log().body()
                .statusCode(200)
                .extract().jsonPath().getInt("Data[1].Id");

        given()
                .spec(getSpecificationWithBearerToken())
                .log().uri()
                .when()
                .get("/Customer/GetCustomerById?id=" +id)
                .then()
                .log().body()
                .statusCode(200)
                .body("Data.Id", equalTo(id));
        ;
    }
}
