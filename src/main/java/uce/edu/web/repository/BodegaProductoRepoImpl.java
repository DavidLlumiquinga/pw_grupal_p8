package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.BodegaProducto;

@Transactional
@ApplicationScoped
public class BodegaProductoRepoImpl implements IBodegaProductoRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertar(BodegaProducto bp) {
        em.persist(bp);
    }

    @Override
    public void actualizar(BodegaProducto bp) {
        em.merge(bp);
    }

    @Override
    public void borrar(Integer id) {
        BodegaProducto bp = this.buscarPorId(id);
        if (bp != null) {
            em.remove(bp);
        }
    }

    @Override
    public BodegaProducto buscarPorId(Integer id) {
        return em.find(BodegaProducto.class, id);
    }

    @Override
    public List<BodegaProducto> buscarTodos() {
        return em.createQuery("SELECT bp FROM BodegaProducto bp", BodegaProducto.class).getResultList();
    }
}
