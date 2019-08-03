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

    /*  OLD
        01 - Created
        02 - Sent for Approval
        03 - Approved
        04 - Rejected
        05 - Modified by Creator
        06 - Modified by Approver
     */

    /*  Current
        01 - Created
        02 - Sent for 1st Level Approval
        03 - First Level Approval Completed
        04 - Rejected
        05 - Sent for Second Level Approval
        06 - Second Level Approval Completed
        07 - Sent for Third Level Approval
        08 - Third Level Approval Completed
     */
 //

    private String code;
    private String description;
}
