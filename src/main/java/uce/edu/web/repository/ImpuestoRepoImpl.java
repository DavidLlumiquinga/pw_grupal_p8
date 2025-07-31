package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.Impuesto;

@Transactional
@ApplicationScoped
public class ImpuestoRepoImpl implements IImpuestoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Impuesto seleccionarPorId(Integer id) {
        return this.entityManager.find(Impuesto.class, id);
    }

    @Override
    public List<Impuesto> seleccionarTodos() {
        TypedQuery<Impuesto> myQuery = this.entityManager.createQuery("SELECT i FROM Impuesto i", Impuesto.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizar(Impuesto impuesto) {
        this.entityManager.merge(impuesto);
    }


    @Override
    public void borrar(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Impuesto impuesto) {
        this.entityManager.persist(impuesto);
    }
}
