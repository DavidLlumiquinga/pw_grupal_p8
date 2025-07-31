package uce.edu.web.service.to;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ImpuestoTo {
    private Integer id;
    private String nombre;
    private BigDecimal porcentaje;
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

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(jakarta.ws.rs.core.UriInfo uriInfo) {
        String self = uriInfo.getBaseUriBuilder()
                .path("impuestos")
                .path(String.valueOf(id))
                .build().toString();
        _links.put("self", self);
    }
}
