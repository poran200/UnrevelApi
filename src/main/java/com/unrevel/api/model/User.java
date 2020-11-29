package com.unrevel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(nullable = false)
    private String fullName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> userRoles;
    private boolean isAccountNotLocked;
    public void addRole(Role role) {
        if (userRoles == null) {
            userRoles = new HashSet<>();
        }
        userRoles.add(role);
    }
    public void addRoles(Set<Role> role){
        if (userRoles == null){
            userRoles = new HashSet<>();
        }
        userRoles.addAll(role);
    }


}
