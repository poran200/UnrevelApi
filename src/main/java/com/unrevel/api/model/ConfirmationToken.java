package com.unrevel.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfirmationToken extends BaseEntity {
    private String confirmationToken;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        this.setCreatedAt( new Date());
        confirmationToken = UUID.randomUUID().toString();
    }

}
