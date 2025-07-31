package uce.edu.web.service.to;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProductoTo {
    private Integer id;
    private String nombre;
    private String categoria;
    private BigDecimal precio;
    private String codigoBarras;
    private Map<String, String> _links = new HashMap<>();

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

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(jakarta.ws.rs.core.UriInfo uriInfo) {
        String self = uriInfo.getBaseUriBuilder()
                .path("productos")
                .path(String.valueOf(id))
                .build().toString();
        _links.put("self", self);
    }
}
