package uce.edu.web.service;

import java.util.List;

import uce.edu.web.service.to.ClienteTo;

public interface IClienteService {
    ClienteTo buscarPorId(Integer id);
    List<ClienteTo> buscarTodos();
    void actualizar(ClienteTo clienteTo);
    void borrar(Integer id);
    void guardar(ClienteTo clienteTo);
}
