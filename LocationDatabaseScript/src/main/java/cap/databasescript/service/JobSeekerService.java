package cap.databasescript.service;

import cap.databasescript.model.Company;
import cap.databasescript.model.CompanyLocation;
import cap.databasescript.model.JobSeeker;
import cap.databasescript.model.Recruiter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class JobSeekerService extends ScriptService<JobSeeker>{

    private static JobSeekerService INSTANCE;

    private JobSeekerService() {
        super(JobSeeker.class);
    }

    public static JobSeekerService getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new JobSeekerService();
        }
        return INSTANCE;
    }

    public List<JobSeeker> getJobSeekers() {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<JobSeeker> query = getCriteriaQuery();
        Root<JobSeeker> jobSeekerBERoot = query.from(JobSeeker.class);
        query.select(jobSeekerBERoot);
        return getEntityManager().createQuery(query).getResultList();
    }

    public String concatenateAddressForResponses(JobSeeker company) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(company.getStreet()).append(" ").append(company.getHouseNumber()).append(",").append(company.getCity()).append(" ").append(" ")
                .append(company.getPostalCode());
        return stringBuilder.toString();
    }

    public JobSeeker getJobSeekerById(long id) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<JobSeeker> query = getCriteriaQuery();
        Root<JobSeeker> jobSeekerBERoot = query.from(JobSeeker.class);
        query.select(jobSeekerBERoot).where(criteriaBuilder.equal(jobSeekerBERoot.get("primaryUserId"), id));
        return getEntityManager().createQuery(query).getSingleResult();
    }
}
