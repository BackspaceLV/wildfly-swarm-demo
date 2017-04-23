package com.example;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/cars")
@ApplicationScoped
@Produces("application/json")
public class CarController {

    @Inject
    private CarRepository carRepository;

    @GET
    @Path("/findAll")
    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }


    @GET
    @Path("/findById")
    public Response findById(@QueryParam("id") int id) {
        return carRepository.findBy(id).map(Response::ok).orElse(Response.status(Response.Status.NOT_FOUND)).build();
    }

    @POST
    @Path("/save")
    public Response save(CarEntity entity) {
        carRepository.save(entity);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete")
    public Response delete(@QueryParam("id") int id) {
        carRepository.delete(id);
        return Response.ok().build();
    }
}