package at.htl.boundary;

import at.htl.control.AccountRepository;
import at.htl.control.DebtorRepository;
import at.htl.control.EntryRepository;
import at.htl.dto.DebtorDto;
import at.htl.entity.Debtor;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/debtor")
public class DebtorResource {

    @Inject
    DebtorRepository debtorRepository;

    @Inject
    EntryRepository entryRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(debtorRepository.listAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(debtorRepository.findById(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Context UriInfo uriInfo, Debtor debtor) {
        Debtor savedDebtor = debtorRepository.save(debtor);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedDebtor.id));

        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Path("/getDebtorsByAccount/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDebtorsByAccount(@PathParam("id") Long id) {
        List<DebtorDto> debtors = debtorRepository.getDebtorsByAccount(id);

        return Response.ok(debtors).build();
    }

    @GET
    @Path("/{id}/entries")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEntriesPerDebtor(@PathParam("id") Long id) {
        return Response.ok(entryRepository.getEntriesByDebtor(id)).build();
    }
}
