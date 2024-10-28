package com.quickpickdeal.api;

import com.quickpickdeal.api.pojo.CreateCustomer;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCustomerTest extends BaseTest{
    @Test
    public void updateCustomer() {
        String email = LoremIpsum.getInstance().getEmail();
        String firstName = LoremIpsum.getInstance().getFirstName();
        String lastName = LoremIpsum.getInstance().getLastName();

        given()
                .spec(getSpecificationWithBearerToken())
                .log().uri()
                .body(new CreateCustomer(email, firstName, lastName, 12))
                .log().body()
                .when()
                .put("/Customer/EditCustomer?id=12")
                .then()
                .log().body()
                .statusCode(200)
                .body("Data.id", equalTo(12))
                .body("Data.Email", equalTo(email))
                .body("Data.FirstName", equalTo(firstName))
                .body("Data.LastName", equalTo(lastName));
    }
}
