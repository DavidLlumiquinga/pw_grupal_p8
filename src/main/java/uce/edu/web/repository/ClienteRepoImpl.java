package uce.edu.web.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.repository.modelo.Cliente;

@Transactional
@ApplicationScoped
public class ClienteRepoImpl implements IClienteRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cliente seleccionarPorId(Integer id) {
        return this.entityManager.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> seleccionarTodos() {
        TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return myQuery.getResultList();
    }

    @Override
    public void actualizar(Cliente cliente) {
        this.entityManager.merge(cliente);
    }

    @Override
    public void borrar(Integer id) {
        this.entityManager.remove(this.seleccionarPorId(id));
    }

    @Override
    public void insertar(Cliente cliente) {
        this.entityManager.persist(cliente);
    }
}
