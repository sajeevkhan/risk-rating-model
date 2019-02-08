package com.pfs.riskmodel.repository;

import com.pfs.riskmodel.domain.CreditRatingMap;
import com.pfs.riskmodel.domain.CreditRatingSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sajeev on 17-Dec-18.
 */
public interface CreditRatingMapRepository extends JpaRepository<CreditRatingMap, Long> {

    List<CreditRatingMap> findByCreditRatingSource(CreditRatingSource creditRatingSource);

    List<CreditRatingMap> findByCreditRatingSourceAndNatureOfRatingOfParentFirm(CreditRatingSource creditRatingSource,
                                                                                         Character natureOfRatingParentFirm);

    CreditRatingMap findByCreditRatingSourceAndCreditRatingAndNatureOfRatingOfParentFirm(CreditRatingSource creditRatingSource,
                                                            String creditRating, Character natureOfRatingParentFirm);

}
