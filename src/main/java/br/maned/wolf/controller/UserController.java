package br.maned.wolf.controller;

import br.maned.wolf.entity.UserEntity;
import br.maned.wolf.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {

        return Response.ok(userService.findAll(page, pageSize)).build();
    }

    @POST
    @Transactional
    public Response createUser(UserEntity user) {
        return Response.ok(userService.createUser(user)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response UpdateUser(@PathParam("id") UUID userId, UserEntity user) {
        return Response.ok(userService.updateUser(userId, user)).build();
    }


    @GET
    @Path("/{id}")
    public Response findUser(@PathParam("id") UUID userId) {
        return Response.ok(userService.findById(userId)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") UUID userId) {
        userService.deleteById(userId);
        return Response.noContent().build();
    }

}
