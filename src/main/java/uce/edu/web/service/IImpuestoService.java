package uce.edu.web.service;

import java.util.List;

import uce.edu.web.service.to.ImpuestoTo;

public interface IImpuestoService {
    void guardar(ImpuestoTo impuestoTo);

    void actualizar(ImpuestoTo impuestoTo);

    void borrar(Integer id);

    ImpuestoTo buscarPorId(Integer id);

    List<ImpuestoTo> buscarTodos();

}
