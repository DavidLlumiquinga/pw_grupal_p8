package uce.edu.web.service;

import java.util.List;

import uce.edu.web.service.to.ProductoTo;


public interface IProductoService {
    ProductoTo buscarPorId(Integer id);
    List<ProductoTo> buscarTodos();
    void actualizar(ProductoTo productoTo);
    void borrar(Integer id);
    void guardar(ProductoTo productoTo);
}
