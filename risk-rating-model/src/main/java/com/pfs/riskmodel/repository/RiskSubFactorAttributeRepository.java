package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.RiskSubFactorAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * Created by sajeev on 05-Dec-18.
 */
@RepositoryRestResource
public interface RiskSubFactorAttributeRepository  extends JpaRepository<RiskSubFactorAttribute, UUID> {


}
