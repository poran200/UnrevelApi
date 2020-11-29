package com.unrevel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RelevantQsAns extends BaseEntity implements Serializable {
    @OneToOne
    private RelevantQuestion question;
    private String answer;
    @ManyToOne
    @JsonIgnoreProperties({"relevantQsAns"})
    private Profile profile;
}
