package uce.edu.web.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.service.IClienteService;
import uce.edu.web.service.to.ClienteTo;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    private IClienteService clienteService;

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ClienteTo cli = clienteService.buscarPorId(id);
        cli.buildURI(uriInfo);
        return Response.ok(cli).build();
    }

    @GET
    public Response consultarTodos() {
        List<ClienteTo> lista = clienteService.buscarTodos();
        return Response.ok(lista).build();
    }

    @POST
    public void guardar(ClienteTo clienteTo) {
        clienteService.guardar(clienteTo);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, ClienteTo clienteTo) {
        clienteTo.setId(id);
        clienteService.actualizar(clienteTo);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Integer id) {
        clienteService.borrar(id);
    }
}
