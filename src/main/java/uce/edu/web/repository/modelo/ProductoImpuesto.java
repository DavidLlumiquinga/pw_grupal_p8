package uce.edu.web.repository.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto_impuesto")
public class ProductoImpuesto {

    @Id
    @Column(name = "prim_id")
    @SequenceGenerator(name = "seq_producto_impuesto", sequenceName = "seq_producto_impuesto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto_impuesto")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "prim_id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "prim_id_impuesto")
    private Impuesto impuesto;


    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public Object getPorcentaje() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPorcentaje'");
    }
}
