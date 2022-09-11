package com.br.login.model.dto;

import java.io.Serializable;
import java.util.Set;

public class UserResponseDTO implements Serializable {
    private final String email;
    private final Set<RoleDTO> roles;

    public UserResponseDTO(String email, Set<RoleDTO> roles) {
        this.email = email;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

}
