package uce.edu.web.repository;

import java.util.List;

import uce.edu.web.repository.modelo.Producto;

public interface IProductoRepo {

    void insertar(Producto producto);

    void actualizar(Producto producto);

    void borrar(Integer id);

    Producto seleccionarPorId(Integer id);

    List<Producto> seleccionarTodos();
}
