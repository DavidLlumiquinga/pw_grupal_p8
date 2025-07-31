package uce.edu.web.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IDetalleFacturaRepo;
import uce.edu.web.repository.modelo.DetalleFactura;
import uce.edu.web.repository.modelo.Impuesto;
import uce.edu.web.repository.modelo.ProductoImpuesto;
import uce.edu.web.service.mapper.DetalleFacturaMapper;
import uce.edu.web.service.to.DetalleFacturaTo;

@ApplicationScoped
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    @Inject
    private IDetalleFacturaRepo detalleFacturaRepo;

    @Override
    public DetalleFacturaTo buscarPorId(Integer id) {
        DetalleFactura detalle = this.detalleFacturaRepo.seleccionarPorId(id);
        return DetalleFacturaMapper.toTo(detalle);
    }

    @Override
    public List<DetalleFacturaTo> buscarTodos() {
        return this.detalleFacturaRepo.seleccionarTodos().stream()
            .map(DetalleFacturaMapper::toTo)
            .collect(Collectors.toList());
    }

    @Override
    public void actualizar(DetalleFacturaTo detalleFacturaTo) {
        this.detalleFacturaRepo.actualizar(DetalleFacturaMapper.toEntity(detalleFacturaTo));
    }

    @Override
    public void borrar(Integer id) {
        this.detalleFacturaRepo.borrar(id);
    }

    @Override
    public void guardar(DetalleFacturaTo detalleFacturaTo) {
        this.detalleFacturaRepo.insertar(DetalleFacturaMapper.toEntity(detalleFacturaTo));
    }


    /**
     * Calcula el total de impuestos de una factura
     */
    public double calcularTotalImpuestos(uce.edu.web.repository.modelo.Factura factura) {
        double totalImpuestos = 0.0;
        if (factura != null && factura.getDetalles() != null) {
            for (DetalleFactura det : factura.getDetalles()) {
                if (det.getProducto() != null && det.getProducto().getProductoImpuestos() != null) {
                    for (ProductoImpuesto imp : det.getProducto().getProductoImpuestos()) {
                        if (imp != null && imp.getPorcentaje() != null && det.getSubtotal() != null) {
                            totalImpuestos += det.getSubtotal().doubleValue() * ((Integer) imp.getPorcentaje()).doubleValue() / 100.0;
                        }
                    }
                }
            }
        }
        return totalImpuestos;
    }

    /**
     * Calcula el total general de la factura (sumatoria de subtotales + impuestos de todos los detalles de la factura)
     */
    public double calcularTotalGeneralFactura(uce.edu.web.repository.modelo.Factura factura) {
        double total = 0.0;
        double totalImpuestos = 0.0;
        if (factura != null && factura.getDetalles() != null) {
            for (DetalleFactura det : factura.getDetalles()) {
                total += det.getSubtotal() != null ? det.getSubtotal().doubleValue() : 0.0;
                if (det.getProducto() != null && det.getProducto().getProductoImpuestos() != null) {
                    for (ProductoImpuesto imp : det.getProducto().getProductoImpuestos()) {
                        if (imp != null && imp.getPorcentaje() != null && det.getSubtotal() != null) {
                            totalImpuestos += det.getSubtotal().doubleValue() * ((Integer) imp.getPorcentaje()).doubleValue() / 100.0;
                        }
                    }
                }
            }
        }
        return total + totalImpuestos;
    }
    
}
