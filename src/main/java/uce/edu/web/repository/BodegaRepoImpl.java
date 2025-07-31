package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.Bodega;

@Transactional
@ApplicationScoped
public class BodegaRepoImpl implements IBodegaRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Bodega seleccionarPorId(Integer id) {
        return this.entityManager.find(Bodega.class, id);
    }

    @Override
    public List<Bodega> seleccionarTodos() {
        TypedQuery<Bodega> myQuery = this.entityManager.createQuery("SELECT b FROM Bodega b", Bodega.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizar(Bodega bodega) {
        this.entityManager.merge(bodega);
    }

    @Override
    public void borrar(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Bodega bodega) {
        this.entityManager.persist(bodega);
    }

       @Override
    public List<Bodega> seleccionarPorCodigoBarras(String codigoBarras) {
        TypedQuery<Bodega> query = this.entityManager.createQuery(
            "SELECT b FROM Bodega b WHERE b.codigo = :codigoBarras", Bodega.class);
        query.setParameter("codigoBarras", codigoBarras);
        return query.getResultList();
    }
    
}
