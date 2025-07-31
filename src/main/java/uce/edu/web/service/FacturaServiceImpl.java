package uce.edu.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IFacturaRepo;
import uce.edu.web.repository.modelo.DetalleFactura;
import uce.edu.web.repository.modelo.Factura;
import uce.edu.web.repository.modelo.ProductoImpuesto;
import uce.edu.web.service.mapper.FacturaMapper;
import uce.edu.web.service.to.FacturaTo;
import uce.edu.web.service.to.ReporteFacturaTo;

@ApplicationScoped
public class FacturaServiceImpl implements IFacturaService {

    @Inject
    private IFacturaRepo facturaRepo;

    @Override
    public FacturaTo buscarPorId(Integer id) {
        Factura factura = this.facturaRepo.seleccionarPorId(id);
        return FacturaMapper.toTo(factura);
    }

    @Override
    public List<FacturaTo> buscarTodos() {
        return this.facturaRepo.seleccionarTodos().stream()
                .map(FacturaMapper::toTo)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizar(FacturaTo facturaTo) {
        this.facturaRepo.actualizar(FacturaMapper.toEntity(facturaTo));
    }

    @Override
    public void borrar(Integer id) {
        this.facturaRepo.borrar(id);
    }

    @Override
    public void guardar(FacturaTo facturaTo) {
        this.facturaRepo.insertar(FacturaMapper.toEntity(facturaTo));
    }

    public List<ReporteFacturaTo> generarReporteFacturas() {
        List<Factura> facturas = facturaRepo.seleccionarTodos();
        List<ReporteFacturaTo> reporte = new ArrayList<>();
        for (Factura factura : facturas) {
            ReporteFacturaTo to = new ReporteFacturaTo();
            to.setNumeroDocumento(factura.getNumeroDocumento());
            to.setIdentificacionCliente(factura.getCliente().getCedula());
            to.setNombreCliente(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
            int cantidadItems = factura.getDetalles().size();
            to.setCantidadItems(cantidadItems);

            double totalImpuestos = 0.0;
            double total = 0.0;
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
            to.setTotalImpuestos(totalImpuestos);
            to.setTotal(total + totalImpuestos);
            reporte.add(to);
        }
        return reporte;
    }
}
