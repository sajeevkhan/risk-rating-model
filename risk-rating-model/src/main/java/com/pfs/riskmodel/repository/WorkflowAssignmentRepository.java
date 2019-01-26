package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.Purpose;
import com.pfs.riskmodel.domain.WorkflowAssignment;
import com.pfs.riskmodel.domain.WorkflowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface WorkflowAssignmentRepository extends JpaRepository<WorkflowAssignment, Long>{

    WorkflowAssignment findByPurpose(Purpose purpose);
}
