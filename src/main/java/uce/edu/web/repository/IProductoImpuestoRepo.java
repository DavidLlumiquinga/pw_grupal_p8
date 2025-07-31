package uce.edu.web.repository;

import java.util.List;

import uce.edu.web.repository.modelo.ProductoImpuesto;

public interface IProductoImpuestoRepo {
    void insertar(ProductoImpuesto pi);
    void actualizar(ProductoImpuesto pi);
    void borrar(Integer id);
    ProductoImpuesto buscarPorId(Integer id);
    List<ProductoImpuesto> buscarTodos();
}
