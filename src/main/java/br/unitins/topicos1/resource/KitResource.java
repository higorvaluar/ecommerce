package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.KitRequestDTO;
import br.unitins.topicos1.dto.KitResponseDTO;
import br.unitins.topicos1.service.KitServiceImpl;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/kits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KitResource {
    @Inject
    KitServiceImpl kitService;

    @POST
    public Response create(@Valid KitRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(kitService.create(dto))
                .build();
    }

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {
        return Response.ok(kitService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public KitResponseDTO findById(@PathParam("id") Long id) {
        return kitService.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, KitRequestDTO dto) {
        kitService.update(id, dto);
        Response response = Response.status(Response.Status.NO_CONTENT).build();
        return response;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        kitService.delete(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<KitResponseDTO> findByNome(@PathParam("nome") String nome) {
        return kitService.findByNome(nome);
    }
}