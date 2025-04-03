package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    public List<ProdutoResponseDTO> getAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public ProdutoResponseDTO getById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public Response create(ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoCriado = service.create(dto);
        return Response.status(Response.Status.CREATED).entity(produtoCriado).build();
    }

    @PUT
    @Path("/{id}")
    public ProdutoResponseDTO update(@PathParam("id") Long id, ProdutoRequestDTO dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    @Path("/categoria/{tipo}")
    public List<ProdutoResponseDTO> getByCategoria(@PathParam("tipo") String categoriaNome) {
        return service.findByCategoria(categoriaNome);
    }
}
