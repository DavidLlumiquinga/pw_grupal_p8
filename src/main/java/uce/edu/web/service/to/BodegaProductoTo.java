package uce.edu.web.service.to;

public class BodegaProductoTo {
    private Integer id;
    private Integer bodegaId;
    private Integer productoId;
    private Integer stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Integer bodegaId) {
        this.bodegaId = bodegaId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
