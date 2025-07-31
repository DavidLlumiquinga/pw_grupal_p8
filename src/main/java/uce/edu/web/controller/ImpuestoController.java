package uce.edu.web.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.service.IImpuestoService;
import uce.edu.web.service.to.ImpuestoTo;

@Path("/impuestos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImpuestoController {

    @Inject
    private IImpuestoService impuestoService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ImpuestoTo imp = impuestoService.buscarPorId(id);
        imp.buildURI(uriInfo);
        return Response.ok(imp).build();
    }

    @GET
    public Response consultarTodos() {
        List<ImpuestoTo> lista = impuestoService.buscarTodos();
        return Response.ok(lista).build();
    }

    @POST
    public void guardar(ImpuestoTo impuestoTo) {
        impuestoService.guardar(impuestoTo);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, ImpuestoTo impuestoTo) {
        impuestoTo.setId(id);
        impuestoService.actualizar(impuestoTo);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        impuestoService.borrar(id);
    }
}
