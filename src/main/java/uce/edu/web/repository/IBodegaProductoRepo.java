package uce.edu.web.repository;

import java.util.List;
import uce.edu.web.repository.modelo.BodegaProducto;

public interface IBodegaProductoRepo {
    void insertar(BodegaProducto bp);
    void actualizar(BodegaProducto bp);
    void borrar(Integer id);
    BodegaProducto buscarPorId(Integer id);
    List<BodegaProducto> buscarTodos();
}
