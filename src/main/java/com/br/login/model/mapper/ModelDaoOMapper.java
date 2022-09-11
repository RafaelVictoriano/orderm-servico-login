package com.br.login.model.mapper;

import com.br.login.model.UserModel;
import com.br.login.model.dto.UserDto;
import com.br.login.model.dto.UserResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class ModelDaoOMapper {

    public abstract UserModel create(UserDto userDto);

    abstract UserModel userResponseDTOToUser(UserResponseDTO userResponseDTO);

    public abstract UserResponseDTO userToUserResponseDTO(UserModel userModel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract void updateUserFromUserResponseDTO(UserResponseDTO userResponseDTO, @MappingTarget UserModel userModel);
}