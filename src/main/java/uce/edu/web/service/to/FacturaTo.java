package uce.edu.web.service.to;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FacturaTo {
    private Integer id;
    private String rucEmpresa;
    private String numeroDocumento;
    private String establecimiento;
    private String puntoEmision;
    private LocalDate fechaEmision;
    private BigDecimal total;
    private BigDecimal totalImpuestos;
    private Integer clienteId;
    private Map<String, String> _links = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getPuntoEmision() {
        return puntoEmision;
    }

    public void setPuntoEmision(String puntoEmision) {
        this.puntoEmision = puntoEmision;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(BigDecimal totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Map<String, String> get_links() {
        return _links;
    }

    public void set_links(Map<String, String> _links) {
        this._links = _links;
    }

    public void buildURI(jakarta.ws.rs.core.UriInfo uriInfo) {
        String self = uriInfo.getBaseUriBuilder()
                .path("facturas")
                .path(String.valueOf(id))
                .build().toString();
        _links.put("self", self);
    }
}
