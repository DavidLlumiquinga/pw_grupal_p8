package uce.edu.web.service;

import java.util.List;

import uce.edu.web.service.to.FacturaTo;
import uce.edu.web.service.to.ReporteFacturaTo;

public interface IFacturaService {
    FacturaTo buscarPorId(Integer id);
    List<FacturaTo> buscarTodos();
    void actualizar(FacturaTo facturaTo);
    void borrar(Integer id);
    void guardar(FacturaTo facturaTo);
    List<ReporteFacturaTo> generarReporteFacturas();
}
