
package uce.edu.web.repository.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "bodega")
public class Bodega {

    @Id
    @SequenceGenerator(name = "seq_bodega", sequenceName = "seq_bodega", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bodega")
    @Column(name = "bode_id")
    private Integer id;

    @Column(name = "bode_codigo")
    private String codigo;

    @Column(name = "bode_nombre")
    private String nombre;

    @Column(name = "bode_ubicacion")
    private String ubicacion;

    // Relaciones
    @OneToMany(mappedBy = "bodega" , fetch = FetchType.LAZY)
    private List<BodegaProducto> bodegaProductos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<BodegaProducto> getBodegaProductos() {
        return bodegaProductos;
    }

    public void setBodegaProductos(List<BodegaProducto> bodegaProductos) {
        this.bodegaProductos = bodegaProductos;
    }

}
