package uce.edu.web.repository;

import java.util.List;

import uce.edu.web.repository.modelo.Factura;

public interface IFacturaRepo {
    void insertar(Factura factura);

    void actualizar(Factura factura);

    void borrar(Integer id);

    Factura seleccionarPorId(Integer id);

    List<Factura> seleccionarTodos();
}
