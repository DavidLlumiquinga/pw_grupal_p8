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
import uce.edu.web.service.IBodegaService;
import uce.edu.web.service.to.BodegaTo;


@Path("/bodegas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BodegaController {
    @Inject
    private IBodegaService bodegaService;

   @GET
    @Path("/{codigoBarras}")
    public Response consultarPorCodigoBarras(@PathParam("codigoBarras") String codigoBarras) {
        List<BodegaTo> bodegas = bodegaService.buscarPorCodigoBarras(codigoBarras);
        return Response.ok(bodegas).build();
    }

    @GET
    @Path("")
    public Response consultarTodos() {
        List<BodegaTo> lista = bodegaService.buscarTodos();
        return Response.ok(lista).build();
    }

    @POST
    @Path("")
    public void guardar(BodegaTo bodegaTo) {
        bodegaService.guardar(bodegaTo);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, BodegaTo bodegaTo) {
        bodegaTo.setId(id);
        bodegaService.actualizar(bodegaTo);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        bodegaService.borrar(id);
    }
}
