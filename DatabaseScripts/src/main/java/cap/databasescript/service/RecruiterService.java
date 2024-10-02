package cap.databasescript.service;

import cap.databasescript.model.Company;
import cap.databasescript.model.Recruiter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class RecruiterService extends ScriptService<Recruiter> {

    private static RecruiterService INSTANCE;

    private RecruiterService() {
        super(Recruiter.class);
    }

    public static RecruiterService getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new RecruiterService();
        }
        return INSTANCE;
    }

    public String getRecruiterLanguageByCompanyId(Long companyId) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Recruiter> query = getCriteriaQuery();
        Root<Recruiter> recruiterRoot = query.from(entity);
        Join<Recruiter, Company> recruiterCompanyJoin = recruiterRoot.join("company");
        query.select(recruiterRoot)
                .where(criteriaBuilder.equal(recruiterCompanyJoin.get("primaryCompanyId"), companyId));
        Recruiter recruiter = getEntityByCriteria(query).setMaxResults(1).getSingleResult();
        return recruiter.getLanguage().toString();
    }
}
