package com.ecommerce.api.controllers;

import com.ecommerce.api.models.dtos.UserDto;
import com.ecommerce.api.models.requests.UserRequest;
import com.ecommerce.api.models.responses.UserExistsResponse;
import com.ecommerce.api.models.responses.UserResponse;
import com.ecommerce.api.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<UserResponse>> getAll() {
        Collection<UserResponse> userResponseCollection = new ArrayList<UserResponse>();
        for (UserDto userDto: this.userService.getAllUsers()) {
            UserResponse userResponse = this.mapper.map(userDto, UserResponse.class);
            userResponseCollection.add(userResponse);
        }

        return new ResponseEntity<>(userResponseCollection, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getAUser(@PathVariable Long id) throws NoSuchElementException {
        UserResponse userResponse;
        try{
            userResponse = this.mapper.map(this.userService.getAUserById(id), UserResponse.class);

        } catch (NoSuchElementException e) {
            log.error("The user doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/getUser/{emailUser}")
    public ResponseEntity<UserResponse> getAUserByEmail(@PathVariable String emailUser) throws NoSuchElementException {
        UserResponse userResponse;
        try{
            userResponse = this.mapper.map(this.userService.getByEmail(emailUser), UserResponse.class);

        } catch (NoSuchElementException e) {
            log.error("The user doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/userExists/{emailUser}")
    public ResponseEntity<UserExistsResponse> userExists (@PathVariable String emailUser) throws NoSuchElementException {
        UserExistsResponse userExistsResponse = new UserExistsResponse();
        try{
            userExistsResponse.setUserExists(this.userService.exists(emailUser));

        } catch (NoSuchElementException e) {
            log.error("The user doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userExistsResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
        UserResponse userResponse = new UserResponse();
        UserDto userDto = this.mapper.map(userRequest, UserDto.class);
        if (!this.userService.exists(userDto.getUserEmail())){
            userResponse = this.mapper.map(this.userService.create(userDto), UserResponse.class);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UserRequest UserRequest, @PathVariable Long id){
        UserDto userDtoUpdate = this.mapper.map(UserRequest, UserDto.class);
        userDtoUpdate.setUserId(id);
        this.userService.update(userDtoUpdate);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.userService.delete(id);
    }

}
