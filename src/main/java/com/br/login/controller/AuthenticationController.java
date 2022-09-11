package com.br.login.controller;


import com.br.login.model.UserModel;
import com.br.login.model.dto.UserDto;
import com.br.login.model.dto.UserResponseDTO;
import com.br.login.model.mapper.ModelDaoOMapper;
import com.br.login.service.UserDetailsServiceImpl;
import com.br.login.util.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.ValidationException;

import static com.br.login.model.Role.ROLES_FUNCIONARIO;
import static com.br.login.model.Role.ROLES_GERENTE;


@RestController
@RequestMapping("/auth")
public record AuthenticationController(UserDetailsServiceImpl userDetailsServiceImpl,
                                       ModelDaoOMapper mapper,
                                       JwtTokenUtil jwtTokenUtil) {


    @PostMapping("login")
    public ResponseEntity<UserResponseDTO> login(Authentication auth) {
        final var user = (UserModel) auth.getPrincipal();

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(mapper.userToUserResponseDTO(user));
    }


    @RolesAllowed({ROLES_FUNCIONARIO, ROLES_GERENTE})
    @PostMapping("register")
    public UserResponseDTO register(@RequestBody UserDto request) throws ValidationException {
        return userDetailsServiceImpl.create(request);
    }

}

