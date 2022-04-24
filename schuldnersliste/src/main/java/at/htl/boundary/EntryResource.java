package at.htl.boundary;

import at.htl.control.EntryRepository;
import at.htl.entity.Entry;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/entry")
public class EntryResource {

    @Inject
    EntryRepository entryRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok(entryRepository.listAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(entryRepository.findById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Context UriInfo uriInfo, Entry entry) {
        Entry savedEntry = entryRepository.save(entry);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(entry.id));

        return Response.created(uriBuilder.build()).build();
    }
}
