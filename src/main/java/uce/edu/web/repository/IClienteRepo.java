package uce.edu.web.repository;

import java.util.List;

import uce.edu.web.repository.modelo.Cliente;

public interface IClienteRepo {

    void insertar(Cliente cliente);

    void actualizar(Cliente cliente);

    void borrar(Integer id);

    Cliente seleccionarPorId(Integer id);

    List<Cliente> seleccionarTodos();

}
