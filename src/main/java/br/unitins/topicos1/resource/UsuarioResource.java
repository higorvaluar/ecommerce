package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.UsuarioRequestDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("api/usuarios")
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
    public Response login(LoginDTO dto) {
        try {
            String token = usuarioService.autenticar(dto.email(), dto.senha());
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    public record LoginDTO(String email, String senha) {

    }
}
