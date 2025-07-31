package uce.edu.web.service;

import java.util.List;

import uce.edu.web.service.to.DetalleFacturaTo;

public interface IDetalleFacturaService {
    DetalleFacturaTo buscarPorId(Integer id);
    List<DetalleFacturaTo> buscarTodos();
    void actualizar(DetalleFacturaTo detalleFacturaTo);
    void borrar(Integer id);
    void guardar(DetalleFacturaTo detalleFacturaTo);
}
