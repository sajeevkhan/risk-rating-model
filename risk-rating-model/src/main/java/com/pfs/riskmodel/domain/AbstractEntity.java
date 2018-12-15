package com.pfs.riskmodel.domain;

import lombok.*;

import javax.persistence.*;
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

//    @Id
//    protected String id;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="ENQ_SEQ")
    @SequenceGenerator(name="ENQ_SEQ",sequenceName="ENQ_SEQ",allocationSize=1)
    private Long id;


//    @PrePersist
//    public void init() {
//        if(this.id == null) {
//            this.id = UUID.randomUUID().toString();
//        }
//    }


}

