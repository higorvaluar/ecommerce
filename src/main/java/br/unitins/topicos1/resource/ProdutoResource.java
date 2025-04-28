package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    @Operation(summary = "Lista todos os produtos", description = "Retorna uma lista de todos os produtos disponíveis")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso"),
            @APIResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Response getAll() {
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Busca produto por ID", description = "Retorna um produto específico pelo seu ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Produto encontrado"),
            @APIResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @POST
    @Operation(summary = "Cria um novo produto", description = "Cria um novo produto com base nos dados fornecidos")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @APIResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Response create(@Valid ProdutoRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
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
        return Response.ok(service.update(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deleta um produto", description = "Remove um produto pelo seu ID")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @APIResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/search/{nome}")
    @Operation(summary = "Busca produtos por nome", description = "Retorna uma lista de produtos que correspondem ao nome fornecido")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/categoria/{categoriaId}")
    @Operation(summary = "Busca produtos por categoria", description = "Retorna uma lista de produtos de uma categoria específica")
    public Response findByCategoria(@PathParam("categoriaId") String categoriaId) {
        return Response.ok(service.findByCategoria(Long.valueOf(categoriaId))).build();
    }
}