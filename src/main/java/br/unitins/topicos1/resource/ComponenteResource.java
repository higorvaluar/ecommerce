package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ComponenteRequestDTO;
import br.unitins.topicos1.dto.ComponenteResponseDTO;
import br.unitins.topicos1.service.ComponenteService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/componentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComponenteResource {

    @Inject
    ComponenteService service;

    @GET
    public List<ComponenteResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public ComponenteResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response create(@Valid ComponenteRequestDTO dto) {
        ComponenteResponseDTO componente = service.create(dto);
        return Response.status(Response.Status.CREATED).entity(componente).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public ComponenteResponseDTO update(@PathParam("id") Long id, @Valid ComponenteRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/search/{nome}")
    public List<ComponenteResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }
}
