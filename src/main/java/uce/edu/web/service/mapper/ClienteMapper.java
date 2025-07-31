package uce.edu.web.service.mapper;

import uce.edu.web.repository.modelo.Cliente;
import uce.edu.web.service.to.ClienteTo;

public class ClienteMapper {
    public static ClienteTo toTo(Cliente cliente) {
        ClienteTo to = new ClienteTo();
        to.setId(cliente.getId());
        to.setCedula(cliente.getCedula());
        to.setNombre(cliente.getNombre());
        to.setApellido(cliente.getApellido());
        to.setRazonSocial(cliente.getRazonSocial());
        to.setDireccion(cliente.getDireccion());
        to.setTelefono(cliente.getTelefono());
        to.setEmail(cliente.getEmail());
        return to;
    }

    public static Cliente toEntity(ClienteTo to) {
        Cliente c = new Cliente();
        c.setId(to.getId());
        c.setCedula(to.getCedula());
        c.setNombre(to.getNombre());
        c.setApellido(to.getApellido());
        c.setRazonSocial(to.getRazonSocial());
        c.setDireccion(to.getDireccion());
        c.setTelefono(to.getTelefono());
        c.setEmail(to.getEmail());
        return c;
    }
}
