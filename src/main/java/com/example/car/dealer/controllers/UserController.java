package com.example.car.dealer.controllers;

import com.example.car.dealer.entities.User;
import com.example.car.dealer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_USER"})
    @GetMapping("")
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String name, @RequestParam(required = false) boolean sortByBirthdate) {
        return ResponseEntity.ok(userService.getAllUsers(name,sortByBirthdate));
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_USER"})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@Validated @RequestBody User user, @PathVariable String id) {
        try {
            User existsUser = userService.getUserById(id);
            user.setId(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
