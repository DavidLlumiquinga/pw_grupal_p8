package uce.edu.web.service.mapper;

import uce.edu.web.repository.modelo.DetalleFactura;
import uce.edu.web.service.to.DetalleFacturaTo;

public class DetalleFacturaMapper {
    public static DetalleFacturaTo toTo(DetalleFactura detalle) {
        DetalleFacturaTo to = new DetalleFacturaTo();
        to.setId(detalle.getId());
        to.setCantidad(detalle.getCantidad());
        to.setPrecioUnitario(detalle.getPrecioUnitario());
        to.setSubtotal(detalle.getSubtotal());
        if (detalle.getFactura() != null) to.setFacturaId(detalle.getFactura().getId());
        if (detalle.getProducto() != null) to.setProductoId(detalle.getProducto().getId());
        if (detalle.getBodega() != null) to.setBodegaId(detalle.getBodega().getId());
        return to;
    }

    public static DetalleFactura toEntity(DetalleFacturaTo to) {
        DetalleFactura d = new DetalleFactura();
        d.setId(to.getId());
        d.setCantidad(to.getCantidad());
        d.setPrecioUnitario(to.getPrecioUnitario());
        d.setSubtotal(to.getSubtotal());
        // Relacionar entidades por id en el service si es necesario
        return d;
    }
}
