package uce.edu.web.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IProductoRepo;
import uce.edu.web.repository.modelo.Producto;
import uce.edu.web.service.mapper.ProductoMapper;
import uce.edu.web.service.to.ProductoTo;

@ApplicationScoped
public class ProductoServiceImpl implements IProductoService {

    @Inject
    private IProductoRepo productoRepo;

    @Override
    public ProductoTo buscarPorId(Integer id) {
        Producto producto = this.productoRepo.seleccionarPorId(id);
        return ProductoMapper.toTo(producto);
    }

    @Override
    public List<ProductoTo> buscarTodos() {
        return this.productoRepo.seleccionarTodos().stream()
            .map(ProductoMapper::toTo)
            .collect(Collectors.toList());
    }

    @Override
    public void actualizar(ProductoTo productoTo) {
        this.productoRepo.actualizar(ProductoMapper.toEntity(productoTo));
    }

    @Override
    public void borrar(Integer id) {
        this.productoRepo.borrar(id);
    }

    @Override
    public void guardar(ProductoTo productoTo) {
        this.productoRepo.insertar(ProductoMapper.toEntity(productoTo));
    }
}
