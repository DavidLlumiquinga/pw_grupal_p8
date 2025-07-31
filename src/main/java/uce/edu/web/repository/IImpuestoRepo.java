package uce.edu.web.repository;

import java.util.List;

import uce.edu.web.repository.modelo.Impuesto;

public interface IImpuestoRepo {

    void insertar(Impuesto impuesto);

    void actualizar(Impuesto impuesto);

    void borrar(Integer id);

    Impuesto seleccionarPorId(Integer id);

    List<Impuesto> seleccionarTodos();

}
