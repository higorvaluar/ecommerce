package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.UsuarioRequestDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @POST
    public Response create(UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.create(dto);
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @POST
    @Path("/login")
    public Response login(@QueryParam("email") String email, @QueryParam("senha") String senha) {
        String token = usuarioService.autenticar(email, senha);
        return Response.ok().entity(token).build();
    }
}
