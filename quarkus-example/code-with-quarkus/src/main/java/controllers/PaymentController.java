package controllers;
import dto.PaymentDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import services.PaymentService;

import java.util.List;

@Path("/payments")
public class PaymentController {
    @Inject
    PaymentService paymentService;
    @GET
    public List<PaymentDto> findAll() {
        return this.paymentService.findAll();
    }
    @GET
    @Path("/{id}")
    public PaymentDto findById(@PathParam("id") Long id) {
        return this.paymentService.findById(id);
    }
    @POST
    public PaymentDto create(PaymentDto orderItemDto) {
        return this.paymentService.create(orderItemDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.paymentService.delete(id);
    }
    @GET
    @Path("/price/{max}")
    public List<PaymentDto> findPaymentsByAmountRangeMax(@PathParam("max") double max) {
        return this.paymentService.findByPriceRange(max);
    }
}