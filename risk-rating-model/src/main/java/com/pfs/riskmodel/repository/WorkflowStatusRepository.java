package com.pfs.riskmodel.repository;

 import com.pfs.riskmodel.domain.ScoreType;
import com.pfs.riskmodel.domain.WorkflowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface WorkflowStatusRepository extends JpaRepository<WorkflowStatus, Long>{

    WorkflowStatus findByCode(String code);
}
