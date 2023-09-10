package org.achumakin;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.achumakin.data.PaymentRepositoryImpl;
import org.achumakin.entities.PaymentRecord;

import java.util.List;

@Path("/payments")
@Produces("application/json")
@Consumes("application/json")
public class Payments {

    @Inject
    PaymentRepositoryImpl paymentRepository;

    @GET
    public List<PaymentRecord> getAllPayments() {
        return paymentRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getPaymentById(@PathParam("id") Long id) {
        var payment = paymentRepository.findById(id);
        if (payment != null) {
            return Response.ok(payment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response createPayment(@Valid PaymentRecord payment) {
        paymentRepository.persist(payment);
        return Response.status(Response.Status.CREATED).entity(payment).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePayment(@PathParam("id") Long id, @Valid PaymentRecord updatedPayment) {
        var existingPayment = paymentRepository.findById(id);
        if (existingPayment != null) {
            existingPayment.setAmount(updatedPayment.getAmount());
            existingPayment.setCurrency(updatedPayment.getCurrency());
            existingPayment.setName(updatedPayment.getName());
            return Response.status(Response.Status.OK).entity(existingPayment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePayment(@PathParam("id") Long id) {
        var existingPayment = paymentRepository.findById(id);
        if (existingPayment != null) {
            paymentRepository.delete(existingPayment);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
