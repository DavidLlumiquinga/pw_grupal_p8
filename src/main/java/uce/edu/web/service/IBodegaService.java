package uce.edu.web.service;

import java.util.List;
import uce.edu.web.service.to.BodegaTo;

public interface IBodegaService {
    BodegaTo buscarPorId(Integer id);
    List<BodegaTo> buscarTodos();
    void actualizar(BodegaTo bodegaTo);
    void borrar(Integer id);
    void guardar(BodegaTo bodegaTo);
    List<BodegaTo> buscarPorCodigoBarras(String codigoBarras);
}
