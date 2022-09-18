package com.br.login.service;

import com.br.login.model.Usuario;
import com.br.login.model.dto.UserDto;
import com.br.login.model.dto.UserResponseDTO;
import com.br.login.model.mapper.UsuarioMapper;
import com.br.login.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;

import static java.lang.String.format;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    final PasswordEncoder passwordEncoder;
    final UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User with username - %s, not found", username)));
        return user;
    }

    @Transactional
    public UserResponseDTO create(UserDto userDto) throws ValidationException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new ValidationException("Usuario já existe!");
        }
        if (!userDto.getSenha().equals(userDto.getReSenha())) {
            throw new ValidationException("Senhas devem ser iguais!");
        }

        final var usuario = this.mapper.create(userDto);
        usuario.setSenha(passwordEncoder.encode(userDto.getSenha()));
        userRepository.save(usuario);
        return this.mapper.userToUserResponseDTO(usuario);
    }

}
