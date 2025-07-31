package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.Factura;

@Transactional
@ApplicationScoped
public class FacturaRepoImpl implements IFacturaRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Factura seleccionarPorId(Integer id) {
        return this.entityManager.find(Factura.class, id);
    }

    @Override
    public List<Factura> seleccionarTodos() {
        TypedQuery<Factura> myQuery = this.entityManager.createQuery("SELECT f FROM Factura f", Factura.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizar(Factura factura) {
        this.entityManager.merge(factura);
    }

    @Override
    public void borrar(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Factura factura) {
        this.entityManager.persist(factura);
    }
}
