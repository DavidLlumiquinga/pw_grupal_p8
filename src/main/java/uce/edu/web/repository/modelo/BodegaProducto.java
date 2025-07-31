package uce.edu.web.repository.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "bodega_producto")
public class BodegaProducto {

     @Id
    @Column(name = "bopr_id")
    @SequenceGenerator(name = "seq_bodega_producto", sequenceName = "seq_bodega_producto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bodega_producto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bopr_id_bodega")
    private Bodega bodega;

    @ManyToOne
    @JoinColumn(name = "bopr_id_producto")
    private Producto producto;

    @Column(name = "bopr_stock")
    private Integer stock;

      /////////////////////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

  
    

}
