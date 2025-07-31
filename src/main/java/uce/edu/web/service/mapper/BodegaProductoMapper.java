package uce.edu.web.service.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.repository.IBodegaRepo;
import uce.edu.web.repository.IProductoRepo;
import uce.edu.web.repository.modelo.Bodega;
import uce.edu.web.repository.modelo.Producto;
import uce.edu.web.repository.modelo.BodegaProducto;
import uce.edu.web.service.to.BodegaProductoTo;

@ApplicationScoped
public class BodegaProductoMapper {

    @Inject
    private IBodegaRepo bodegaRepo;
    @Inject
    private IProductoRepo productoRepo;

    public BodegaProducto toModelo(BodegaProductoTo to) {
        BodegaProducto bp = new BodegaProducto();
        bp.setId(to.getId());
        bp.setStock(to.getStock());
        if (to.getBodegaId() != null) {
            Bodega bodega = bodegaRepo.seleccionarPorId(to.getBodegaId());
            bp.setBodega(bodega);
        }
        if (to.getProductoId() != null) {
            Producto producto = productoRepo.seleccionarPorId(to.getProductoId());
            bp.setProducto(producto);
        }
        return bp;
    }

    public BodegaProductoTo toTo(BodegaProducto bp) {
        BodegaProductoTo to = new BodegaProductoTo();
        to.setId(bp.getId());
        to.setStock(bp.getStock());
        if (bp.getBodega() != null) {
            to.setBodegaId(bp.getBodega().getId());
        }
        if (bp.getProducto() != null) {
            to.setProductoId(bp.getProducto().getId());
        }
        return to;
    }
}
