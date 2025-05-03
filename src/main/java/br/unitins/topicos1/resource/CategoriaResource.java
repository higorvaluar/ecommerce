package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.CategoriaRequestDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.service.CategoriaServiceImpl;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.ok;

@Path("/api/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaServiceImpl categoriaService;

    @POST
    public Response create(@Valid CategoriaRequestDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(categoriaService.create(dto))
                .build();
    }

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
    ) {
        return Response.ok(categoriaService.findAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public CategoriaResponseDTO getById(@PathParam("id") Long id) {
        return categoriaService.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CategoriaRequestDTO dto) {
        categoriaService.update(id, dto);
        Response response = Response.status(Response.Status.NO_CONTENT).build();
        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            categoriaService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar categoria: " + e.getMessage()).build();
        }
    }
}