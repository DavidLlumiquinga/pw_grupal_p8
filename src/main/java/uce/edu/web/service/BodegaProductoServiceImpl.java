package uce.edu.web.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IBodegaProductoRepo;
import uce.edu.web.repository.modelo.BodegaProducto;
import uce.edu.web.service.mapper.BodegaProductoMapper;
import uce.edu.web.service.to.BodegaProductoTo;

@ApplicationScoped
public class BodegaProductoServiceImpl implements IBodegaProductoService {

    @Inject
    private IBodegaProductoRepo repo;

    @Inject
    private BodegaProductoMapper mapper;

    @Override
    public void guardar(BodegaProductoTo to) {
        BodegaProducto bp = mapper.toModelo(to);
        repo.insertar(bp);
    }

    @Override
    public void actualizar(BodegaProductoTo to) {
        BodegaProducto bp = mapper.toModelo(to);
        repo.actualizar(bp);
    }

    @Override
    public void borrar(Integer id) {
        repo.borrar(id);
    }

    @Override
    public BodegaProductoTo buscarPorId(Integer id) {
        BodegaProducto bp = repo.buscarPorId(id);
        return mapper.toTo(bp);
    }

    @Override
    public List<BodegaProductoTo> buscarTodos() {
        return repo.buscarTodos().stream().map(mapper::toTo).collect(Collectors.toList());
    }
}
