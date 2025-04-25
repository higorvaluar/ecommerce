package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.PedidoRequestDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    @GET
    @RolesAllowed("ADMIN")
    public List<PedidoResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public PedidoResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @RolesAllowed("USER")
    public Response create(@Valid PedidoRequestDTO dto) {
        PedidoResponseDTO pedido = service.create(dto);
        return Response.status(Response.Status.CREATED).entity(pedido).build();
    }

    @PUT
    @Path("/{id}/status")
    @RolesAllowed("ADMIN")
    public PedidoResponseDTO updateStatus(@PathParam("id") Long id, @QueryParam("status") String status) {
        return service.updateStatus(id, status);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/usuario/{usuarioId}")
    @RolesAllowed({"ADMIN", "USER"})
    public List<PedidoResponseDTO> findByUsuario(@PathParam("usuarioId") Long usuarioId) {
        return service.findByUsuario(usuarioId);
    }
}