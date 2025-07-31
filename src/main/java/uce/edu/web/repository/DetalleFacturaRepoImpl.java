package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.DetalleFactura;

@Transactional
@ApplicationScoped
public class DetalleFacturaRepoImpl implements IDetalleFacturaRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DetalleFactura seleccionarPorId(Integer id) {
        return this.entityManager.find(DetalleFactura.class, id);
    }

    @Override
    public List<DetalleFactura> seleccionarTodos() {
        TypedQuery<DetalleFactura> myQuery = this.entityManager.createQuery("SELECT d FROM DetalleFactura d", DetalleFactura.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizar(DetalleFactura detalleFactura) {
        this.entityManager.merge(detalleFactura);
    }
    
    @Override
    public void borrar(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(DetalleFactura detalleFactura) {
        this.entityManager.persist(detalleFactura);
    }
}
