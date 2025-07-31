package uce.edu.web.repository;

import java.util.List;

import uce.edu.web.repository.modelo.Bodega;

public interface IBodegaRepo {

    public void insertar (Bodega bodega);

    public void actualizar( Bodega bodega);

    public void borrar(Integer id);

    public Bodega seleccionarPorId(Integer id);

    public List<Bodega> seleccionarTodos();

      public List<Bodega> seleccionarPorCodigoBarras(String codigoBarras);
}
