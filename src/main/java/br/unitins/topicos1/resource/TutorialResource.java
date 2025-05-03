package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.TutorialRequestDTO;
import br.unitins.topicos1.dto.TutorialResponseDTO;
import br.unitins.topicos1.service.TutorialServiceImpl;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/tutoriais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TutorialResource {

    @Inject
    TutorialServiceImpl tutorialService;

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {
        return Response.ok(tutorialService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public TutorialResponseDTO findById(@PathParam("id") Long id) {
        return tutorialService.findById(id);
    }

    @POST
    public Response create(@Valid TutorialRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(tutorialService.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TutorialRequestDTO dto) {
        tutorialService.update(id, dto);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        tutorialService.delete(id);
    }

    @GET
    @Path("/produto/{id}")
    public List<TutorialResponseDTO> findByProduto(@PathParam("id") Long produtoId) {
        return tutorialService.findByProduto(produtoId);
    }
}