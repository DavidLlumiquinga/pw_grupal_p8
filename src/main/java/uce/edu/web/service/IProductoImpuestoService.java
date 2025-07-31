package uce.edu.web.service;

import java.util.List;

import uce.edu.web.service.to.ProductoImpuestoTo;

public interface IProductoImpuestoService {
    void guardar(ProductoImpuestoTo to);
    void actualizar(ProductoImpuestoTo to);
    void borrar(Integer id);
    ProductoImpuestoTo buscarPorId(Integer id);
    List<ProductoImpuestoTo> buscarTodos();
}
