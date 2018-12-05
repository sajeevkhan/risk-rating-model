package com.pfs.riskmodel.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Created by sajeev on 05-Dec-18.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractEntity {

    @Id
    protected String id;

    @PrePersist
    public void init() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }


}

