package controllers;

import dto.ReviewDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import services.ReviewService;

import java.util.List;

@Path("/reviews")
public class ReviewController {
    @Inject
    ReviewService reviewService;
    @GET @Path("/product/{id}")
    public List<ReviewDto> findAllByProduct(@PathParam("id") Long id) {
        return this.reviewService.findReviewsByProductId(id);
    }
    @GET @Path("/{id}")
    public ReviewDto findById(@PathParam("id") Long id) {
        return this.reviewService.findById(id);
    }
    @POST @Path("/product/{id}")
    public ReviewDto create(ReviewDto reviewDto, @PathParam("id") Long id) {
        return this.reviewService.create(reviewDto, id);
    }
    @DELETE @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.reviewService.delete(id);
    }
}