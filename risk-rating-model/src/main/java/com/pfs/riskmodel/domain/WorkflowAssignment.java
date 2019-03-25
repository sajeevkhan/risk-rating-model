package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by sajeev on 26-Jan-19.
 */

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class WorkflowAssignment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    /*
        01 - Project Assessment
        02 - Risk Assessment
        03 - Monitoring
     */

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private RiskPurpose purpose;


    private String firstLevelApproverName;
    private String firstLevelApproverEmailId;
    private String secondLevelApproverName;
    private String secondLevelApproverEmailId;
    private String thirdLevelApproverName;
    private String thirdLevelApproverEmailId;

}
