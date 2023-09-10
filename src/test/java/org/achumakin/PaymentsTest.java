package org.achumakin;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.achumakin.entities.PaymentRecord;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@Testcontainers
@QuarkusTestResource(DatabaseTestResource.class)
public class PaymentsTest {

    @Inject
    Payments payments;

    @Test
    public void getAllPayments() {
        given()
          .when().get("/payments")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testCreatePayment() {
        var payment = new PaymentRecord();
        payment.setAmount(11.13);
        payment.setCurrency("USD");
        payment.setName("admin");

        given()
                .contentType(ContentType.JSON)
                .body(payment)
                .when()
                .post("/payments")
                .then()
                .statusCode(201); // Expect a 201 (Created) status code
    }

    @Test
    public void testUpdatePayment() {
        // Assuming you have an existing payment with ID 1
        Long paymentId = 1L;

        var updatedPayment = new PaymentRecord();
        updatedPayment.setAmount(11.12);
        updatedPayment.setCurrency("USD");
        updatedPayment.setName("admin");

        given()
                .contentType(ContentType.JSON)
                .body(updatedPayment)
                .when()
                .put("/payments/{id}", paymentId)
                .then()
                .statusCode(204); // Expect a 204 (No Content) status code for a successful update
    }

    @Test
    public void testDeletePayment() {
        // Assuming you have an existing payment with ID 1
        var paymentId = 1L;

        given()
                .when()
                .delete("/payments/{id}", paymentId)
                .then()
                .statusCode(204); // Expect a 204 (No Content) status code for a successful delete
    }

}
