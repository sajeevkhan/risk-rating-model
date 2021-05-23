package com.pfs.riskmodel.dto;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.After;

import java.util.Date;

/**
 * Created by sajeev on 23-May-21.
 */
@Getter
@Setter
public class RiskModelReportDTO {

      //    Loannumber
            private String loanNumber;
      //    Project Name
            private String projectName;
      //    Project Type
            private String projectType;
      //    Project Phase
            private String projectPhase;
      //    InitiatingDepartment
            private String InitiatingDepartment;
      //    Loan contractamount(Rs Crores)
            private Double loanContractAmount;
      //    Total DisbursedAmt(Rs Crores)
            private Double totalLoanDisbursedAmount;
      //    Initiator
            private String initiator;
      // Creation date
            private Date createDate;
      //    Process date (After finalapproval)
            private  Date processDate;
      //    FinalRating
            private String finalRating;





}
