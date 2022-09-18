package com.br.login.model.mapper;

import com.br.login.model.Usuario;
import com.br.login.model.dto.UserDto;
import com.br.login.model.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {

    @Mapping(target = "perfil.nome", source = "perfil")
    public abstract Usuario create(UserDto userDto);

    @Mapping(target = "perfil", source = "perfil.nome")
    public abstract UserResponseDTO userToUserResponseDTO(Usuario usuario);

}
