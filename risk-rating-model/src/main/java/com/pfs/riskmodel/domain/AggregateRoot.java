package com.pfs.riskmodel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

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
public abstract class AggregateRoot<A extends AbstractAggregateRoot<A>> extends AbstractAggregateRoot<A> implements Serializable{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    protected Long id;
//@Temporal(TemporalType.TIMESTAMP)
//@Column(name = "created_at", nullable = false, updatable = false)
//@CreatedDate
//private Date createdAt;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_at", nullable = false)
//    @LastModifiedDate
//    private Date updatedAt;
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
}