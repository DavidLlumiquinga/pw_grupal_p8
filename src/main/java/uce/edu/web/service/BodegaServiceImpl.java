package uce.edu.web.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IBodegaRepo;
import uce.edu.web.repository.modelo.Bodega;
import uce.edu.web.service.mapper.BodegaMapper;
import uce.edu.web.service.to.BodegaTo;


@ApplicationScoped
public class BodegaServiceImpl implements IBodegaService {

    @Inject
    private IBodegaRepo bodegaRepo;

    @Override
    public BodegaTo buscarPorId(Integer id) {
        Bodega bodega = this.bodegaRepo.seleccionarPorId(id);
        return BodegaMapper.toTo(bodega);
    }

    @Override
    public List<BodegaTo> buscarTodos() {
        return this.bodegaRepo.seleccionarTodos().stream()
            .map(BodegaMapper::toTo)
            .collect(Collectors.toList());
    }

    @Override
    public void actualizar(BodegaTo bodegaTo) {
        this.bodegaRepo.actualizar(BodegaMapper.toEntity(bodegaTo));
    }

    @Override
    public void borrar(Integer id) {
        this.bodegaRepo.borrar(id);
    }

    @Override
    public void guardar(BodegaTo bodegaTo) {
        this.bodegaRepo.insertar(BodegaMapper.toEntity(bodegaTo));
    }

    public List<BodegaTo> buscarPorCodigoBarras(String codigoBarras) {
        return this.bodegaRepo.seleccionarPorCodigoBarras(codigoBarras)
            .stream()
            .map(BodegaMapper::toTo)
            .collect(Collectors.toList());
    }
    
}
