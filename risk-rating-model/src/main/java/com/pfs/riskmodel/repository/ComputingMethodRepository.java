package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.ComputingMethod;
import com.pfs.riskmodel.domain.ScoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by sajeev on 17-Dec-18.
 */

@Repository
@RepositoryRestResource
public interface ComputingMethodRepository extends JpaRepository<ComputingMethod, Long>{

    ComputingMethod findByCode(String code);
}
