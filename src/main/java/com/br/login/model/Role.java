package com.br.login.model;

import com.br.login.enumearation.RoleName;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {

    public static final String ROLES_GERENTE = "ROLES_GERENTE";
    public static final String ROLES_FUNCIONARIO = "ROLES_FUNCIONARIO";
    public static final String ROLES_CLIENTE = "ROLES_CLIENTE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName.toString();
    }
}
