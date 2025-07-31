
package uce.edu.web.repository.modelo;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_facturas")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "defa_id")
    private Integer id;

    @Column(name = "defa_cantidad")
    private Integer cantidad;

    @Column(name = "defa_precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "defa_subtotal")
    private BigDecimal subtotal;

    /// relaciones
    @ManyToOne
    @JoinColumn(name = "defa_id_fact")
    private Factura factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defa_id_prod")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "defa_id_bodega")
    private Bodega bodega;

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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public double getTotal() {
        double total = 0.0;
        total += getSubtotal() != null ? getSubtotal().doubleValue() : 0.0;
        return total;
    }

}
