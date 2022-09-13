package com.br.login.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTO implements Serializable {
    private final String email;
    private final String nome;
    private final Set<RoleDTO> roles;

    public UserResponseDTO(String email, String nome, Set<RoleDTO> roles) {
        this.email = email;
        this.nome = nome;
        this.roles = roles;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

}
