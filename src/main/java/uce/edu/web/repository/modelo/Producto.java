package uce.edu.web.repository.modelo;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Integer id;

    @Column(name = "prod_nombre")
    private String nombre;

    @Column(name = "prod_categoria")
    private String categoria;

    @Column(name = "prod_precio")
    private BigDecimal precio;

    @Column(name = "prod_codigo_barras")
    private String codigoBarras;

    // Relaciones
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<BodegaProducto> bodegaProductos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoImpuesto> productoImpuestos;

    @ManyToMany
    @JoinTable(name = "producto_bodega", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "bodega_id"))
    private List<Bodega> bodegas;

    ////////////////////////////////

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public List<BodegaProducto> getBodegaProductos() {
        return bodegaProductos;
    }

    public void setBodegaProductos(List<BodegaProducto> bodegaProductos) {
        this.bodegaProductos = bodegaProductos;
    }

    public List<ProductoImpuesto> getProductoImpuestos() {
        return productoImpuestos;
    }

    public void setProductoImpuestos(List<ProductoImpuesto> productoImpuestos) {
        this.productoImpuestos = productoImpuestos;
    }

}
