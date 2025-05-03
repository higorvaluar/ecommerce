package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.PedidoRequestDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.service.PedidoServiceImpl;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoServiceImpl pedidoService;

    @GET
    public List<PedidoResponseDTO> getAll(@QueryParam("page") @DefaultValue("0") int page,
                                          @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return pedidoService.findAll(page, pageSize);
    }

    @POST
    public Response create(@Valid PedidoRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(pedidoService.create(dto)).build();
    }

    @GET
    @Path("/usuario/{usuarioId}")
    public List<PedidoResponseDTO> findByUsuario(@PathParam("usuarioId") Long usuarioId) {
        return pedidoService.findByUsuario(usuarioId);
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(pedidoService.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid PedidoRequestDTO dto) {
        pedidoService.update(id, dto);
        Response response = Response.status(Response.Status.NO_CONTENT).build();
        return response;
    }

    @PUT
    @Path("/{id}/status")
    public Response updateStatus(@PathParam("id") Long id, @QueryParam("status") String status) {
        return Response.ok(pedidoService.updateStatus(id, status)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        pedidoService.delete(id);
        return Response.noContent().build();
    }
}