package com.oryode.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER_ROLES",uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ROLE_NAME", "USER_NAME" }))
public class UserRole implements Serializable {

    public enum Role{
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_COMPANY,
        ROLE_CC
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID", unique = true, nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_NAME", nullable = false)
    private User user;

    @Column(name = "USER_ROLE_NAME", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private Role role;
}
