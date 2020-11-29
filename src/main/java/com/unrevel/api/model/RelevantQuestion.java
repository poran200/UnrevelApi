package com.unrevel.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class RelevantQuestion extends BaseEntity implements Serializable {
    private String question;
}
