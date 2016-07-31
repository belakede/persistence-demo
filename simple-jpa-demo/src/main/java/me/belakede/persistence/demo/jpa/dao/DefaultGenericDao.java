package me.belakede.persistence.demo.jpa.dao;

import me.belakede.persistence.demo.jpa.domain.PersistentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DefaultGenericDao<T extends PersistentEntity> implements GenericDao<T> {

    private final Class<T> type;
    private final EntityManagerFactory factory;

    public DefaultGenericDao(Class<T> type) {
        this.type = type;
        this.factory = Persistence.createEntityManagerFactory("DemoPU");
    }

    @Override
    public Long create(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return entity.getId();
    }

    @Override
    public void update(T entity) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = getEntityManager();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<T> findAll() {
        return findEntities(true, -1, -1);
    }

    @Override
    public T findById(Integer id) {
        return getEntityManager().find(type, id);
    }

    private EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    @Override
    public void clearCache() {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
    }

    private List<T> findEntities(boolean all, int firstResult, int maxResult) {
        EntityManager entityManager = getEntityManager();
        CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(type));
        Query query = entityManager.createQuery(criteriaQuery);
        if (!all) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

}
