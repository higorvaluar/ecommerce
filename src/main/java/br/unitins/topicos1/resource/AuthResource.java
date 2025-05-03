package br.unitins.topicos1.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import br.unitins.topicos1.service.AuthService;
import br.unitins.topicos1.dto.LoginRequestDTO;
import br.unitins.topicos1.dto.TokenResponseDTO;
import br.unitins.topicos1.dto.ErrorResponseDTO;

@Path("/auth")
public class AuthResource {
    @Inject
    AuthService service;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO dto) {
        try {
            String token = service.login(dto.email, dto.senha);
            return Response.ok(new TokenResponseDTO(token)).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus())
                    .entity(new ErrorResponseDTO(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponseDTO("Erro interno: " + e.getMessage()))
                    .build();
        }
    }
}