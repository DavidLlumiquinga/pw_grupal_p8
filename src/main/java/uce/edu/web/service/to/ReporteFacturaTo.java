package uce.edu.web.service.to;

public class ReporteFacturaTo {
    private String numeroDocumento;
    private String identificacionCliente;
    private String nombreCliente;
    private Integer cantidadItems;
    private Double totalImpuestos;
    private Double total;

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public Double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(Double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
