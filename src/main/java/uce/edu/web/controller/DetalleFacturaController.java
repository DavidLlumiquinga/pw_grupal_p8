package uce.edu.web.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.service.IDetalleFacturaService;
import uce.edu.web.service.to.DetalleFacturaTo;

@Path("/detalles-factura")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DetalleFacturaController {

    @Inject
    private IDetalleFacturaService detalleFacturaService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        DetalleFacturaTo det = detalleFacturaService.buscarPorId(id);
        det.buildURI(uriInfo);
        return Response.ok(det).build();
    }

    @GET
    public Response consultarTodos() {
        List<DetalleFacturaTo> lista = detalleFacturaService.buscarTodos();
        return Response.ok(lista).build();
    }

    @POST
    public void guardar(DetalleFacturaTo detalleFacturaTo) {
        detalleFacturaService.guardar(detalleFacturaTo);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, DetalleFacturaTo detalleFacturaTo) {
        detalleFacturaTo.setId(id);
        detalleFacturaService.actualizar(detalleFacturaTo);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        detalleFacturaService.borrar(id);
    }
}
