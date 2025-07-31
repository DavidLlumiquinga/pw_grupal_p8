package uce.edu.web.service.mapper;

import uce.edu.web.repository.modelo.Bodega;
import uce.edu.web.service.to.BodegaTo;

public class BodegaMapper {
    public static BodegaTo toTo(Bodega bodega) {
        BodegaTo to = new BodegaTo();
        to.setId(bodega.getId());
        to.setCodigo(bodega.getCodigo());
        to.setNombre(bodega.getNombre());
        to.setUbicacion(bodega.getUbicacion());
        return to;
    }

    public static Bodega toEntity(BodegaTo to) {
        Bodega b = new Bodega();
        b.setId(to.getId());
        b.setCodigo(to.getCodigo());
        b.setNombre(to.getNombre());
        b.setUbicacion(to.getUbicacion());
        return b;
    }
}
