package com.oryode.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({@NamedQuery(name = "@GET_BY_USERNAME", query = "FROM User WHERE username = :username"),
               @NamedQuery(name = "@GET_ALL_USERS", query = "FROM User WHERE firstName IS NOT NULL AND lastName IS NOT NULL"),
               @NamedQuery(name = "@GET_ALL_COMPANY_USERS", query = "FROM User WHERE company.companyName = :companyName"),
               @NamedQuery(name = "@GET_USERS_BY_IDS", query = "FROM User WHERE id IN :ids")
})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE")
    private String phone;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="COM_ID")
    private Company company;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ADR_ID")
    private Address address;

    @Column(name="USER_NAME")
    private String username;

    @Column(name="USER_PASSWORD")
    private String password;

    @Column(name="IS_ENABLED")
    private boolean enabled = true;

    @Column(name="IS_LOCKED")
    private boolean locked = false;

    public String getFullAddress(){
        if(address==null){
            return "";
        }
        return address.getAddress();
    }
    public Company.CompanyType getCompanyType(){
        return this.company.getCompanyType();
    }
}
