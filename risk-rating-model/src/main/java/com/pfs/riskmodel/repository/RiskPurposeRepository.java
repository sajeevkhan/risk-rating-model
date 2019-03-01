package com.pfs.riskmodel.repository;

 import com.pfs.riskmodel.domain.RiskPurpose;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 import org.springframework.stereotype.Component;
 import org.springframework.stereotype.Repository;

/**
 * Created by sajeev on 17-Dec-18.
 */
@Component
@Repository
@RepositoryRestResource
public interface RiskPurposeRepository extends JpaRepository<RiskPurpose, Long>{

    RiskPurpose findByCode(String code);
}
