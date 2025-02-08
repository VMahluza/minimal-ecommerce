package com.minimal_ecommerce.minimal_ecommerce.services;
import com.minimal_ecommerce.minimal_ecommerce.models.User;

import java.util.List;
import java.util.Optional;
public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long Id);
    User updateUser(Long Id, User updatedUser);
    void deleteUser(Long Id);
}
