package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ComponenteRequestDTO;
import br.unitins.topicos1.dto.ComponenteResponseDTO;
import br.unitins.topicos1.service.ComponenteServiceImpl;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/componentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComponenteResource {

    @Inject
    ComponenteServiceImpl componenteService;

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {
        return Response.ok(componenteService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(componenteService.findById(id)).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response create(@Valid ComponenteRequestDTO dto) {
        ComponenteResponseDTO componente = componenteService.create(dto);
        return Response.status(Response.Status.CREATED).entity(componente).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response update(@PathParam("id") Long id, @Valid ComponenteRequestDTO dto) {
        componenteService.update(id, dto);
        Response response = Response.status(Response.Status.NO_CONTENT).build();
        return response;
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") Long id) {
        componenteService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/search/{nome}")
    public List<ComponenteResponseDTO> findByNome(@PathParam("nome") String nome) {
        return componenteService.findByNome(nome);
    }
}