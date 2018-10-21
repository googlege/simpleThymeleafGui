package de.homedev.thymeleaf.backend.service.api;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import de.homedev.thymeleaf.api.model.UserEntity;
import de.homedev.thymeleaf.api.model.UserRoleEntity;

public interface IUserService extends UserDetailsService {
    public static final String SERVICE_NAME = "userServiceImpl";

    public UserEntity saveUser(UserEntity user);

    public UserRoleEntity saveAuthority(UserRoleEntity entity);

    public void withdrawAuthorityByUser(UserEntity userEntity, UserRoleEntity newAuthorityEntity);

    public List<UserEntity> findUser(String username, String userPassword);

    public List<UserRoleEntity> findByAuthorityName(String authorityName);

    @Override
    public UserDetails loadUserByUsername(String username);

}
