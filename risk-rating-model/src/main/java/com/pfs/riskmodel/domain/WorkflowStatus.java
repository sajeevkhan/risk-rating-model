package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class WorkflowStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    /*
        01 - Created
        02 - Sent for Approval
        03 - Approved
        04 - Rejected
        05 - Modified by Creator
        06 - Modified by Approver
     */
    private String code;
    private String description;
}
