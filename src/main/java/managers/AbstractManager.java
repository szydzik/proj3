/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nb
 */
public abstract class AbstractManager<T> {

    private Class<T> entityClass;

    @PersistenceContext(name = "pl.bsb_proj3_war_1.0-SNAPSHOTPU")
    EntityManager entityManager;

    public AbstractManager(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void edit(T entity) {
        entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }

    public T find(Object id) {
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(entityManager.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = entityManager.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
