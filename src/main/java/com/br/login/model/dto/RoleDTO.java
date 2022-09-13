package com.br.login.model.dto;

import com.br.login.enumearation.RoleName;

public class RoleDTO {

    private RoleName roleName;

    public RoleDTO(RoleName roleName) {
        this.roleName = roleName;
    }

    public RoleName getRoleName() {
        return roleName;
    }
}
