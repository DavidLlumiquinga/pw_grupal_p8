package uce.edu.web.repository;

import java.util.List;

import uce.edu.web.repository.modelo.DetalleFactura;

public interface IDetalleFacturaRepo {

    void insertar(DetalleFactura detalle);

    void actualizar(DetalleFactura detalle);

    void borrar(Integer id);

    DetalleFactura seleccionarPorId(Integer id);

    List<DetalleFactura> seleccionarTodos();

}
