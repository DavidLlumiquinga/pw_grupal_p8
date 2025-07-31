package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.Producto;

@Transactional
@ApplicationScoped
public class ProductoRepoImpl implements IProductoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Producto seleccionarPorId(Integer id) {
        return this.entityManager.find(Producto.class, id);
    }

    @Override
    public List<Producto> seleccionarTodos() {
        TypedQuery<Producto> myQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizar(Producto producto) {
        this.entityManager.merge(producto);
    }


    @Override
    public void borrar(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Producto producto) {
        this.entityManager.persist(producto);
    }
}
