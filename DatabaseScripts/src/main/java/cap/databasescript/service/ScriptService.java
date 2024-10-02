package cap.databasescript.service;

import cap.databasescript.model.Company;
import cap.databasescript.model.Recruiter;
import lombok.Getter;

import jakarta.persistence.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ScriptService<T> {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistance-unit");

    Class<T> entity;

    @PersistenceContext
    @Getter
    private EntityManager entityManager = entityManagerFactory.createEntityManager();


    public ScriptService(Class<T> entity) {
        this.entity = entity;
    }

    public void create(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        entityManager.flush();
        transaction.commit();
    }

    protected void update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(entity);
        entityManager.flush();
        transaction.commit();
    }

    public TypedQuery<T> getEntityByCriteria(CriteriaQuery<T> query) {
        return entityManager.createQuery(query);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return getCriteriaBuilder().createQuery(entity);
    }
}
