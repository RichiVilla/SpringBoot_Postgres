package org.adaschool.datapostgres.controller;

import org.adaschool.datapostgres.data.dto.UserDto;
import org.adaschool.datapostgres.data.repository.UserRepository;
import org.adaschool.datapostgres.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.adaschool.datapostgres.data.entity.User;

import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    User createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    User findById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else throw new UserNotFoundException();
    }

}

