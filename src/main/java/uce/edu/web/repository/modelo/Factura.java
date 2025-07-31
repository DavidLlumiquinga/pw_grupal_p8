package uce.edu.web.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "fact_id")
    private Integer id;

    @Column(name = "fact_ruc_empresa")
    private String rucEmpresa;

    @Column(name = "fact_numero_documento")
    private String numeroDocumento;

    @Column(name = "fact_establecimiento")
    private String establecimiento;

    @Column(name = "fact_punto_emision")
    private String puntoEmision;

    @Column(name = "fact_fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fact_total")
    private BigDecimal total;

    @Column(name = "fact_total_impuestos")
    private BigDecimal totalImpuestos;

    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "fact_id_clie")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<DetalleFactura> detalles;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public java.math.BigDecimal getTotal() {
        return total;
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }

    public java.math.BigDecimal getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(java.math.BigDecimal totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

}
