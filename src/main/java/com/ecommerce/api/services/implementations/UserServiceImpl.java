package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.UserDto;
import com.ecommerce.api.models.entities.UserEntity;
import com.ecommerce.api.repositories.UserRepository;
import com.ecommerce.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Collection<UserDto> getAllUsers() {
        Collection<UserDto> userDtosCollection = new ArrayList<UserDto>();
        for (UserEntity userEntity : this.userRepository.findAll()){
            UserDto userDto = this.mapper.map(userEntity, UserDto.class);
            userDtosCollection.add(userDto);
        }
        return userDtosCollection;
    }

    @Override
    public UserDto getAUserById(Long id) {
        return this.mapper.map(this.userRepository.findById(id).get(), UserDto.class);
    }

    @Override
    public UserDto getByEmail(String emailUser) {
        for (UserEntity userEntity : this.userRepository.findAll()) {
            if (userEntity.getUserEmail().equals(emailUser)) {
                return this.mapper.map(this.userRepository.findById(userEntity.getUserId()).get(), UserDto.class);
            }
        }
        return null;
    }

    @Override
    public void update(UserDto userDto) {
        UserEntity userEntityUpdate = this.mapper.map(userDto, UserEntity.class);

        if (userDto.getUserEmail() != null) {
            userEntityUpdate.setUserEmail(userDto.getUserEmail());
        }
        this.userRepository.save(userEntityUpdate);
    }

    @Override
    public UserDto create(UserDto userDto) {
        UserEntity userEntity = this.userRepository.save(this.mapper.map(userDto, UserEntity.class));
        System.out.println("userid service: " + userEntity.getUserId());
        return this.mapper.map(this.userRepository.findById(userEntity.getUserId()).get(), UserDto.class);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Boolean exists(String userEmail) {
        for (UserEntity userEntity : this.userRepository.findAll()) {
            if (userEntity.getUserEmail().equals(userEmail)){
                return true;
            }
        }
        return false;
    }
}
