package uce.edu.web.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.service.IProductoService;
import uce.edu.web.service.to.ProductoTo;


@Path("/productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoController {

    @Inject
    private IProductoService productoService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProductoTo prod = productoService.buscarPorId(id);
        prod.buildURI(uriInfo);
        return Response.ok(prod).build();
    }

    @GET
    public Response consultarTodos() {
        List<ProductoTo> lista = productoService.buscarTodos();
        return Response.ok(lista).build();
    }

    @POST
    public void guardar(ProductoTo producto) {
        productoService.guardar(producto);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, ProductoTo producto) {
        producto.setId(id);
        productoService.actualizar(producto);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        productoService.borrar(id);
    }
}
