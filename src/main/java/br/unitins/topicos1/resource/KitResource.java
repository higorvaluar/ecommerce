package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.KitRequestDTO;
import br.unitins.topicos1.dto.KitResponseDTO;
import br.unitins.topicos1.service.KitService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/kits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KitResource {
    @Inject
    KitService service;

    @POST
    public Response create(KitRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @GET
    public List<KitResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public KitResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public KitResponseDTO update(@PathParam("id") Long id, KitRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<KitResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }
}
