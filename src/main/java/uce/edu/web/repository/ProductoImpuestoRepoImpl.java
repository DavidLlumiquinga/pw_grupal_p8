package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.ProductoImpuesto;

@ApplicationScoped
@Transactional
public class ProductoImpuestoRepoImpl implements IProductoImpuestoRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertar(ProductoImpuesto pi) {
        em.persist(pi);
    }

    @Override
    public void actualizar(ProductoImpuesto pi) {
        em.merge(pi);
    }

    @Override
    public void borrar(Integer id) {
        ProductoImpuesto pi = this.buscarPorId(id);
        if (pi != null) {
            em.remove(pi);
        }
    }

    @Override
    public ProductoImpuesto buscarPorId(Integer id) {
        return em.find(ProductoImpuesto.class, id);
    }

    @Override
    public List<ProductoImpuesto> buscarTodos() {
        return em.createQuery("SELECT pi FROM ProductoImpuesto pi", ProductoImpuesto.class).getResultList();
    }
}
