package at.htl.boundary;

import at.htl.control.AccountRepository;
import at.htl.entity.Account;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/account")
public class AccountResource {

    @Inject
    AccountRepository accountRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(accountRepository.listAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(accountRepository.findById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Context UriInfo uriInfo, Account account) {
        Account savedAccount = accountRepository.save(account);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedAccount.id));

        return Response.created(uriBuilder.build()).build();
    }
}
