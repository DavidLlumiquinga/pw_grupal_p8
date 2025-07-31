package uce.edu.web.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IProductoImpuestoRepo;
import uce.edu.web.repository.modelo.ProductoImpuesto;
import uce.edu.web.service.mapper.ProductoImpuestoMapper;
import uce.edu.web.service.to.ProductoImpuestoTo;

@ApplicationScoped
public class ProductoImpuestoServiceImpl implements IProductoImpuestoService {

    @Inject
    private IProductoImpuestoRepo repo;

    @Inject
    private ProductoImpuestoMapper mapper;

    @Override
    public void guardar(ProductoImpuestoTo to) {
        ProductoImpuesto pi = mapper.toModelo(to);
        repo.insertar(pi);
    }

    @Override
    public void actualizar(ProductoImpuestoTo to) {
        ProductoImpuesto pi = mapper.toModelo(to);
        repo.actualizar(pi);
    }

    @Override
    public void borrar(Integer id) {
        repo.borrar(id);
    }

    @Override
    public ProductoImpuestoTo buscarPorId(Integer id) {
        ProductoImpuesto pi = repo.buscarPorId(id);
        return mapper.toTo(pi);
    }

    @Override
    public List<ProductoImpuestoTo> buscarTodos() {
        return repo.buscarTodos().stream().map(mapper::toTo).collect(Collectors.toList());
    }
}
