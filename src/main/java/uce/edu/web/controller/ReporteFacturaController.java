package uce.edu.web.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.service.IFacturaService;
import uce.edu.web.service.to.ReporteFacturaTo;

@Path("/reporte-facturas")
public class ReporteFacturaController {

    @Inject
    private IFacturaService facturaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarReporte() {
        List<ReporteFacturaTo> reporte = facturaService.generarReporteFacturas();
        return Response.ok(reporte).build();
    }
}
