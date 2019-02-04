package com.pfs.riskmodel.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by sajeev on 15-Dec-18.
 */
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ChangeDocumentItem extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer itemNo;

    private String entityName;

    private String attributeName;

    private String tableKey;

    private String entityDescription;

    private String oldValue;

    private String newValue;




}
