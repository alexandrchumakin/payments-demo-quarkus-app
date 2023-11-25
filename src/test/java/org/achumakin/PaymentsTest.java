package org.achumakin;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import jakarta.inject.Inject;
import org.achumakin.entities.PaymentRecord;
import org.achumakin.mock.DatabaseTestResource;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@Testcontainers
@QuarkusTestResource(DatabaseTestResource.class)
public class PaymentsTest {

    @SuppressWarnings("unused")
    @Inject
    Payments payments;

    private static final Header authHeader = new Header("Authorization", "Basic dXNlcjp1c2Vy");

    @Test
    public void testPaymentCRUD() {
        var payment = new PaymentRecord();
        payment.setAmount(11.13);
        payment.setCurrency("USD");
        payment.setName("admin");

        var createdPayment = given()
                .header(authHeader)
                .contentType(ContentType.JSON)
                .body(payment)
                .when()
                .post("/payments")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(PaymentRecord.class);
        payment.setAmount(24.16);
        getPayments().body("", hasSize(greaterThan(0)));
        given()
                .header(authHeader)
                .contentType(ContentType.JSON)
                .body(createdPayment)
                .when()
                .put("/payments/{id}", createdPayment.getId())
                .then()
                .statusCode(200);
        given()
                .header(authHeader)
                .when()
                .delete("/payments/{id}", createdPayment.getId())
                .then()
                .statusCode(204);
        getPayments().body(is("[]"));
    }

    private ValidatableResponse getPayments() {
        return given()
                .header(authHeader)
                .when()
                .get("/payments")
                .then()
                .statusCode(200);
    }

}
