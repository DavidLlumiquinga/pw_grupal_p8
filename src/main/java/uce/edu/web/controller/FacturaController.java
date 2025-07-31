package uce.edu.web.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.service.IFacturaService;
import uce.edu.web.service.to.FacturaTo;

@Path("/facturas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaController {

    @Inject
    private IFacturaService facturaService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        FacturaTo fac = facturaService.buscarPorId(id);
        fac.buildURI(uriInfo);
        return Response.ok(fac).build();
    }

    @GET
    public Response consultarTodos() {
        List<FacturaTo> lista = facturaService.buscarTodos();
        return Response.ok(lista).build();
    }

    @POST
    public void guardar(FacturaTo facturaTo) {
        facturaService.guardar(facturaTo);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, FacturaTo facturaTo) {
        facturaTo.setId(id);
        facturaService.actualizar(facturaTo);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        facturaService.borrar(id);
    }
}
