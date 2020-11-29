package com.unrevel.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@MappedSuperclass
public abstract class  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    @Column(updatable = false)
    private String createdBy;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private String  updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private  Date updatedAt;
    private Boolean isActive;

    @PrePersist
    public void setPreInsertData(){
        this.createdAt = new Date();
        this.isActive = true;
    }
    @PreUpdate
    public void setPostUpdateData(){
        this.updatedAt = new Date();
    }
}
