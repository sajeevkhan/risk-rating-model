package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
public class WorkflowAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    /*
        01 - Project Assessment
        02 - Risk Assessment
        03 - Monitoring
     */

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private RiskPurpose purpose;

    private String  approverUserName;
    private String  approverEmailId;

}
