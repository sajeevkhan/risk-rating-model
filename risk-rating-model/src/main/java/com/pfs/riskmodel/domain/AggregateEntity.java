package com.pfs.riskmodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by sajeev on 15-Dec-18.
 */

@ToString
@MappedSuperclass
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public abstract class AggregateEntity<A extends AbstractAggregateRoot<A>> extends AbstractAggregateRoot<A> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

//    @Version
//    @JsonIgnore
//    protected Long version;
//
//    protected LocalDate createdOn;
//
//    protected LocalTime createdAt;
//
//    protected String createdByUserName;
//
//    protected LocalDate changedOn;
//
//    protected LocalTime changedAt;
//
//    protected String changedByUserName;

//    @PrePersist
//    public void init() {
//        if (this.id == null) {
//            this.id = UUID.randomUUID();
//        }
//    }
}