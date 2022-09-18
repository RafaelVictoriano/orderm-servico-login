package com.br.login.controller;


import com.br.login.model.Usuario;
import com.br.login.model.dto.UserDto;
import com.br.login.model.dto.UserResponseDTO;
import com.br.login.model.mapper.UsuarioMapper;
import com.br.login.service.UserDetailsServiceImpl;
import com.br.login.util.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.ValidationException;

import static com.br.login.model.Perfil.ROLES_FUNCIONARIO;
import static com.br.login.model.Perfil.ROLES_GERENTE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public record AuthenticationController(UserDetailsServiceImpl userDetailsServiceImpl,
                                       UsuarioMapper mapper,
                                       JwtTokenUtil jwtTokenUtil) {


    @PostMapping("login")
    public ResponseEntity<?> login(Authentication auth) {
        final var user = (UserDetails) auth.getPrincipal();

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(user);
    }


   @RolesAllowed(ROLES_GERENTE)
    @PostMapping("register")
    public UserResponseDTO register(@RequestBody UserDto request) throws ValidationException {
        return userDetailsServiceImpl.create(request);
    }

}

