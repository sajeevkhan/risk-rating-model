package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

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
public abstract class AbstractEntity {

    @Id
    protected String id;

    @PrePersist
    public void init() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }


}

