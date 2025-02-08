package com.minimal_ecommerce.minimal_ecommerce.services.Impl;

import com.minimal_ecommerce.minimal_ecommerce.models.User;
import com.minimal_ecommerce.minimal_ecommerce.repositories.UserRepository;
import com.minimal_ecommerce.minimal_ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> getUserById(Long Id) {
        return userRepository.findById(Id);
    }
    @Override
    public User updateUser(Long Id, User updatedUser) {
        /*
         * Get the user by id using findById
         * since the findById returns a user
         * set the user returned to the updated user
         * then use the repository to save the user
         * if then the user is not found you must make an exception
         * */
        return userRepository.findById(Id).map(user-> {
            user.setEmail(updatedUser.getEmail());
            user.setName(updatedUser.getName());
            user.setUsername(updatedUser.getSurname());
            user.setSurname(updatedUser.getSurname());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User with this id is not found"));
    }
    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }
}
