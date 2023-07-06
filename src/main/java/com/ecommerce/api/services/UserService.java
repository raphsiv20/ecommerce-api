package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.ProductDto;
import com.ecommerce.api.models.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface UserService {
    Collection<UserDto> getAllUsers();
    UserDto getAUserById(Long id);
    UserDto getByEmail(String email);
    void update(UserDto userDto);
    UserDto create(UserDto userDto);
    void delete(Long id);
    Boolean exists(String userMail);
}
