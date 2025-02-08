package com.minimal_ecommerce.minimal_ecommerce.controllers;

import com.minimal_ecommerce.minimal_ecommerce.models.User;
import com.minimal_ecommerce.minimal_ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/users
    @PostMapping
    public User createUser (@RequestBody User user) {
        return userService.createUser(user);
    }

    // GET /api/users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    // GET /api/users/{Id}
    @GetMapping("/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable  Long Id){
        return userService.getUserById(Id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // PUT /api/users/{Id}
    @PutMapping("/{Id}")
    public ResponseEntity<User> updateUser(@PathVariable Long Id, @RequestBody User user){
        try {
            User updatedUser = userService.updateUser(Id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    // DELETE /api/user/{Id}
    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long Id){
        userService.deleteUser(Id);
        return ResponseEntity.noContent().build();
    }
}
