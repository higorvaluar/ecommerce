package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.TutorialRequestDTO;
import br.unitins.topicos1.dto.TutorialResponseDTO;
import br.unitins.topicos1.service.TutorialService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/tutoriais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TutorialResource {

    @Inject
    TutorialService service;

    @GET
    public List<TutorialResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public TutorialResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public Response create(@Valid TutorialRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public TutorialResponseDTO update(@PathParam("id") Long id, TutorialRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    @Path("/produto/{id}")
    public List<TutorialResponseDTO> findByProduto(@PathParam("id") Long produtoId) {
        return service.findByProduto(produtoId);
    }
}