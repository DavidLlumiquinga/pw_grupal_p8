package uce.edu.web.service;

import java.util.List;
import uce.edu.web.service.to.BodegaProductoTo;

public interface IBodegaProductoService {
    void guardar(BodegaProductoTo to);
    void actualizar(BodegaProductoTo to);
    void borrar(Integer id);
    BodegaProductoTo buscarPorId(Integer id);
    List<BodegaProductoTo> buscarTodos();
}
