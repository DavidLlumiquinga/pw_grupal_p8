package uce.edu.web.service.mapper;

import uce.edu.web.repository.modelo.Factura;
import uce.edu.web.service.to.FacturaTo;

public class FacturaMapper {
    public static FacturaTo toTo(Factura factura) {
        FacturaTo to = new FacturaTo();
        to.setId(factura.getId());
        to.setRucEmpresa(factura.getRucEmpresa());
        to.setNumeroDocumento(factura.getNumeroDocumento());
        to.setEstablecimiento(factura.getEstablecimiento());
        to.setPuntoEmision(factura.getPuntoEmision());
        to.setFechaEmision(factura.getFechaEmision());
        to.setTotal(factura.getTotal());
        to.setTotalImpuestos(factura.getTotalImpuestos());
        if (factura.getCliente() != null) to.setClienteId(factura.getCliente().getId());
        return to;
    }

    public static Factura toEntity(FacturaTo to) {
        Factura f = new Factura();
        f.setId(to.getId());
        f.setRucEmpresa(to.getRucEmpresa());
        f.setNumeroDocumento(to.getNumeroDocumento());
        f.setEstablecimiento(to.getEstablecimiento());
        f.setPuntoEmision(to.getPuntoEmision());
        f.setFechaEmision(to.getFechaEmision());
        f.setTotal(to.getTotal());
        f.setTotalImpuestos(to.getTotalImpuestos());
        // Relacionar cliente por id en el service si es necesario
        return f;
    }
}
