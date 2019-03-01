package com.pfs.riskmodel.repository;

 import com.pfs.riskmodel.domain.RiskPurpose;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.domain.WorkflowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.rest.core.annotation.RepositoryRestResource;
 import org.springframework.stereotype.Repository;

/**
 * Created by sajeev on 17-Dec-18.
 */

@Repository
@RepositoryRestResource
public interface WorkflowAssignmentRepository extends JpaRepository<WorkflowAssignment, Long>{

    WorkflowAssignment findByPurpose(RiskPurpose purpose);
}
