package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.service.ProdutoServiceImpl;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/api/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoServiceImpl produtoService;

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {
        return Response.ok(produtoService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Busca produto por ID", description = "Retorna um produto específico pelo seu ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Produto encontrado"),
            @APIResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(produtoService.findById(id)).build();
    }

    @POST
    @Operation(summary = "Cria um novo produto", description = "Cria um novo produto com base nos dados fornecidos")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @APIResponse(responseCode = "400", description = "Dados inválidos")
    })

    public Response create(@Valid ProdutoRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(produtoService.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualiza um produto", description = "Atualiza os dados de um produto existente")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @APIResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Response update(@PathParam("id") Long id, @Valid ProdutoRequestDTO dto) {
        produtoService.update(id, dto);
        Response response = Response.status(Response.Status.NO_CONTENT).build();
        return response;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        produtoService.delete(id);
        Response response = Response.status(Response.Status.NO_CONTENT).build();
        return response;
    }

    @GET
    @Path("/search/{nome}")
    @Operation(summary = "Busca produtos por nome", description = "Retorna uma lista de produtos que correspondem ao nome fornecido")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(produtoService.findByNome(nome)).build();
    }

    @GET
    @Path("/categoria/{categoriaId}")
    @Operation(summary = "Busca produtos por categoria", description = "Retorna uma lista de produtos de uma categoria específica")
    public Response findByCategoria(@PathParam("categoriaId") Long categoriaId) {
        return Response.ok(produtoService.findByCategoria(categoriaId)).build();
    }
}