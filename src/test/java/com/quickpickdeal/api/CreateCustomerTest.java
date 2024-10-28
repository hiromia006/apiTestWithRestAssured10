package com.quickpickdeal.api;

import com.quickpickdeal.api.pojo.CreateCustomer;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCustomerTest extends BaseTest {
    @Test
    public void createCustomer() {
        String email = LoremIpsum.getInstance().getEmail();
        String firstName = LoremIpsum.getInstance().getFirstName();
        String lastName = LoremIpsum.getInstance().getLastName();

        given()
                .spec(getSpecificationWithBearerToken())
                .log().uri()
                .body(new CreateCustomer(email, firstName, lastName))
                .log().body()
                .when()
                .post("/Customer/CreateCustomer")
                .then()
                .log().body()
                .statusCode(200)
                .body("Data.Email", equalTo(email))
                .body("Data.FirstName", equalTo(firstName))
                .body("Data.LastName", equalTo(lastName));
    }
}
