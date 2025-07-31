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
import uce.edu.web.service.IProductoImpuestoService;
import uce.edu.web.service.to.ProductoImpuestoTo;

@Path("/producto-impuestos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoImpuestoController {
    @Inject
    private IProductoImpuestoService service;

    @POST
    public Response guardar(ProductoImpuestoTo to) {
        service.guardar(to);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, ProductoImpuestoTo to) {
        to.setId(id);
        service.actualizar(to);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        service.borrar(id);
    }

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id) {
        ProductoImpuestoTo to = service.buscarPorId(id);
        return Response.ok(to).build();
    }

    @GET
    public Response consultarTodos() {
        List<ProductoImpuestoTo> lista = service.buscarTodos();
        return Response.ok(lista).build();
    }
}
