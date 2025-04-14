package br.unitins.topicos1.resource;

import br.unitins.topicos1.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService service;

    @POST
    @Path("/login")
    public Response login(LoginDTO dto) {
        String token = service.login(dto.email(), dto.senha());
        return Response.ok().entity(token).build();
    }
}

record LoginDTO(String email, String senha) {}