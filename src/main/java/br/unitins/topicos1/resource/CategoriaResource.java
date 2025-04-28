package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.CategoriaRequestDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.service.CategoriaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import static jakarta.ws.rs.core.Response.ok;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @POST
    public Response create(CategoriaRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {
        return Response.ok(service.getAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public CategoriaResponseDTO getById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public CategoriaResponseDTO update(@PathParam("id") Long id, CategoriaRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }
}