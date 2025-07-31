package uce.edu.web.service.mapper;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IImpuestoRepo;
import uce.edu.web.repository.IProductoRepo;
import uce.edu.web.repository.modelo.ProductoImpuesto;
import uce.edu.web.service.to.ProductoImpuestoTo;

@ApplicationScoped
public class ProductoImpuestoMapper {

    @Inject
    private IProductoRepo productoRepo;
    @Inject
    private IImpuestoRepo impuestoRepo;

    public ProductoImpuesto toModelo(ProductoImpuestoTo to) {
        ProductoImpuesto pi = new ProductoImpuesto();
        pi.setId(to.getId());
        if (to.getProductoId() != null) {
            pi.setProducto(productoRepo.seleccionarPorId(to.getProductoId()));
        }
        if (to.getImpuestoId() != null) {
            pi.setImpuesto(impuestoRepo.seleccionarPorId(to.getImpuestoId()));
        }
        // Otros campos
        return pi;
    }

    public ProductoImpuestoTo toTo(ProductoImpuesto pi) {
        ProductoImpuestoTo to = new ProductoImpuestoTo();
        to.setId(pi.getId());
        if (pi.getProducto() != null) {
            to.setProductoId(pi.getProducto().getId());
        }
        if (pi.getImpuesto() != null) {
            to.setImpuestoId(pi.getImpuesto().getId());
        }
        // Otros campos
        return to;
    }
}
