package uce.edu.web.service.mapper;

import uce.edu.web.repository.modelo.Impuesto;
import uce.edu.web.service.to.ImpuestoTo;

public class ImpuestoMapper {
    public static ImpuestoTo toTo(Impuesto impuesto) {
        ImpuestoTo to = new ImpuestoTo();
        to.setId(impuesto.getId());
        to.setNombre(impuesto.getNombre());
        to.setPorcentaje(impuesto.getPorcentaje());
        return to;
    }

    public static Impuesto toEntity(ImpuestoTo to) {
        Impuesto i = new Impuesto();
        i.setId(to.getId());
        i.setNombre(to.getNombre());
        i.setPorcentaje(to.getPorcentaje());
        return i;
    }
}
