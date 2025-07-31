package uce.edu.web.repository.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "impuesto")
public class Impuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "impu_id")
    private Integer id;

    @Column(name = "impu_nombre")
    private String nombre;

    @Column(name = "impu_porcentaje")
    private BigDecimal porcentaje;

    @OneToMany(mappedBy = "impuesto", cascade = CascadeType.ALL)
    private List<ProductoImpuesto> productoImpuestos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public List<ProductoImpuesto> getProductoImpuestos() {
        return productoImpuestos;
    }

    public void setProductoImpuestos(List<ProductoImpuesto> productoImpuestos) {
        this.productoImpuestos = productoImpuestos;
    }


}
