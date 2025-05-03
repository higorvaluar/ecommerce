package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.UsuarioRequestDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioServiceImpl;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioServiceImpl usuarioService;

    @GET
    public List<UsuarioResponseDTO> getAll(@QueryParam("page") @DefaultValue("0") int page,
                                           @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return usuarioService.findAll(page, pageSize);
    }

    @POST
    public Response create(@Valid UsuarioRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(usuarioService.create(dto)).build();
    }

    @GET
    @Path("/email/{email}")
    public Response findByEmail(@PathParam("email") String email) {
        return Response.ok(usuarioService.findByEmail(email)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid UsuarioRequestDTO dto) {
        usuarioService.update(id, dto);
        Response response = Response.status(Response.Status.NO_CONTENT).build();
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.noContent().build();
    }
}