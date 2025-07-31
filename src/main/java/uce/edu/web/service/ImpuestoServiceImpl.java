package uce.edu.web.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IImpuestoRepo;
import uce.edu.web.repository.modelo.Impuesto;
import uce.edu.web.service.mapper.ImpuestoMapper;
import uce.edu.web.service.to.ImpuestoTo;

@ApplicationScoped
public class ImpuestoServiceImpl implements IImpuestoService {

    @Inject
    private IImpuestoRepo impuestoRepo;

    @Override
    public ImpuestoTo buscarPorId(Integer id) {
        Impuesto impuesto = this.impuestoRepo.seleccionarPorId(id);
        return ImpuestoMapper.toTo(impuesto);
    }

    @Override
    public List<ImpuestoTo> buscarTodos() {
        return this.impuestoRepo.seleccionarTodos().stream()
            .map(ImpuestoMapper::toTo)
            .collect(Collectors.toList());
    }

    @Override
    public void actualizar(ImpuestoTo impuestoTo) {
        this.impuestoRepo.actualizar(ImpuestoMapper.toEntity(impuestoTo));
    }

    @Override
    public void borrar(Integer id) {
        this.impuestoRepo.borrar(id);
    }

    @Override
    public void guardar(ImpuestoTo impuestoTo) {
        this.impuestoRepo.insertar(ImpuestoMapper.toEntity(impuestoTo));
    }
}
