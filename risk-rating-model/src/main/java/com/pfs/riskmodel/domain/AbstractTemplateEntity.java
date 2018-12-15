package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by sajeev on 05-Dec-18.
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractTemplateEntity {



     @Id
     private Long id;




}

