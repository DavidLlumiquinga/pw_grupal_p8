package uce.edu.web.service.to;

import java.util.HashMap;
import java.util.Map;

public class BodegaTo {

     private Integer id;
    private String codigo;
    private String nombre;
    private String ubicacion;
    private Map<String, String> _links = new HashMap<>();

    //////////////////////////
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
    public Map<String, String> get_links() {
        return _links;
    }
    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }


    public void buildURI(jakarta.ws.rs.core.UriInfo uriInfo) {
        String self = uriInfo.getBaseUriBuilder()
            .path("bodegas")
            .path(String.valueOf(id))
            .build().toString();
        _links.put("self", self);
    }

}
