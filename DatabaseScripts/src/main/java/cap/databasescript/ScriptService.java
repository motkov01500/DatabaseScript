package cap.databasescript;

import lombok.Getter;

import jakarta.persistence.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ScriptService {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistance-unit");

    @PersistenceContext
    @Getter
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private static ScriptService INSTANCE;

    private ScriptService() {
    }

    public static ScriptService getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new ScriptService();
        }
        return INSTANCE;
    }

    public List<Company> getAllCompanies() {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Company> criteriaQuery = getCriteriaQuery();
        Root<Company> companyRoot = criteriaQuery.from(Company.class);
        criteriaQuery.select(companyRoot);
        return  getEntityByCriteria(criteriaQuery).getResultList();
    }

    public TypedQuery<Company> getEntityByCriteria(CriteriaQuery<Company> query) {
        return entityManager.createQuery(query);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    public CriteriaQuery<Company> getCriteriaQuery() {
        return getCriteriaBuilder().createQuery(Company.class);
    }
}
