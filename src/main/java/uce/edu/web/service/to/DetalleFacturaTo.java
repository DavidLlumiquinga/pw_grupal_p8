package uce.edu.web.service.to;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DetalleFacturaTo {
    private Integer id;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private Integer facturaId;
    private Integer productoId;
    private Integer bodegaId;
    private Map<String, String> _links = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Integer bodegaId) {
        this.bodegaId = bodegaId;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(jakarta.ws.rs.core.UriInfo uriInfo) {
        String self = uriInfo.getBaseUriBuilder()
                .path("detalles-factura")
                .path(String.valueOf(id))
                .build().toString();
        _links.put("self", self);
    }
}
