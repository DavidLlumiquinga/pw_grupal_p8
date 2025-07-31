package uce.edu.web.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.service.IBodegaProductoService;
import uce.edu.web.service.to.BodegaProductoTo;

@Path("/bodega-productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BodegaProductoController {

    @Inject
    private IBodegaProductoService bodegaProductoService;

    @POST
    public Response guardar(BodegaProductoTo to) {
        bodegaProductoService.guardar(to);
        return Response.status(Response.Status.CREATED).build(); // 201 Created
    }

    @GET
    public Response consultarTodos() {
        List<BodegaProductoTo> lista = bodegaProductoService.buscarTodos();
        return Response.ok(lista).build(); // 200 OK
    }

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id) {
        BodegaProductoTo to = bodegaProductoService.buscarPorId(id);
        if (to == null) {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 Not Found
        }
        return Response.ok(to).build(); // 200 OK
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, BodegaProductoTo to) {
        to.setId(id); // asegúrate de que el TO tenga el ID correcto
        bodegaProductoService.actualizar(to);
        return Response.ok().build(); // 200 OK
    }

    @DELETE
    @Path("/{id}")
    public Response borrar(@PathParam("id") Integer id) {
        bodegaProductoService.borrar(id);
        return Response.noContent().build(); // 204 No Content
    }

}
