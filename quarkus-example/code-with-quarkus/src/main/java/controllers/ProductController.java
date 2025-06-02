package controllers;

import dto.ProductDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import services.ProductService;

import java.util.List;

@Path("/products")
public class ProductController {
    @Inject
    ProductService productService;
    @GET
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }
    @GET @Path("/count")
    public Long countAllProducts() {
        return this.productService.countAll();
    }
    @GET @Path("/{id}")
    public ProductDto findById(@PathParam("id") Long id) {
        return this.productService.findById(id);
    }
    @POST     public ProductDto create(ProductDto productDto) {
        return this.productService.create(productDto);
    }
    @DELETE @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.productService.delete(id);
    }
    @GET @Path("/category/{id}")
    public List<ProductDto> findByCategoryId(@PathParam("id") Long id) {
        return this.productService.findByCategoryId(id);
    }
    @GET @Path("/count/category/{id}")
    public Long countByCategoryId(@PathParam("id") Long id) {
        return this.productService.countByCategoryId(id);
    }
}