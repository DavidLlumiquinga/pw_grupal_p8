package uce.edu.web.service.mapper;

import uce.edu.web.repository.modelo.Producto;
import uce.edu.web.service.to.ProductoTo;

public class ProductoMapper {
    public static ProductoTo toTo(Producto producto) {
        ProductoTo to = new ProductoTo();
        to.setId(producto.getId());
        to.setNombre(producto.getNombre());
        to.setCategoria(producto.getCategoria());
        to.setPrecio(producto.getPrecio());
        to.setCodigoBarras(producto.getCodigoBarras());
        return to;
    }

    public static Producto toEntity(ProductoTo to) {
        Producto p = new Producto();
        p.setId(to.getId());
        p.setNombre(to.getNombre());
        p.setCategoria(to.getCategoria());
        p.setPrecio(to.getPrecio());
        p.setCodigoBarras(to.getCodigoBarras());
        return p;
    }
}
