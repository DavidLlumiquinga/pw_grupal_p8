package uce.edu.web.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IClienteRepo;
import uce.edu.web.repository.modelo.Cliente;
import uce.edu.web.service.mapper.ClienteMapper;
import uce.edu.web.service.to.ClienteTo;

@ApplicationScoped
public class ClienteServiceImpl implements IClienteService {

    @Inject
    private IClienteRepo clienteRepo;

    @Override
    public ClienteTo buscarPorId(Integer id) {
        Cliente cliente = this.clienteRepo.seleccionarPorId(id);
        return ClienteMapper.toTo(cliente);
    }

    @Override
    public List<ClienteTo> buscarTodos() {
        return this.clienteRepo.seleccionarTodos().stream()
            .map(ClienteMapper::toTo)
            .collect(Collectors.toList());
    }

    @Override
    public void actualizar(ClienteTo clienteTo) {
        this.clienteRepo.actualizar(ClienteMapper.toEntity(clienteTo));
    }

    @Override
    public void borrar(Integer id) {
        this.clienteRepo.borrar(id);
    }

    @Override
    public void guardar(ClienteTo clienteTo) {
        this.clienteRepo.insertar(ClienteMapper.toEntity(clienteTo));
    }
}
