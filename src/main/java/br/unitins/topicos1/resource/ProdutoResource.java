package br.unitins.topicos1.resource;

import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    @Inject
    ProdutoService service;

    @GET
    public List<Produto> getAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Produto getById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    public Produto create(Produto produto) {
        return service.create(produto);
    }

    @PUT
    @Path("/{id}")
    public Produto update(@PathParam("id") Long id, Produto produto) {
        return service.update(id, produto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    @Path("/categoria/{tipo}")
    public List<Produto> getByCategoria(@PathParam("tipo") Categoria categoria) {
        return service.findByCategoria(categoria);
    }
}
