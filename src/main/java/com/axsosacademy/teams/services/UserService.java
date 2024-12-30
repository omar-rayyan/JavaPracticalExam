package com.axsosacademy.teams.services;

import com.axsosacademy.teams.models.LoginUser;
import com.axsosacademy.teams.models.User;
import com.axsosacademy.teams.repos.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User newUser, BindingResult bindingResult) {
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            bindingResult.rejectValue("email", "email.exists", "This email address was already used before. Please enter a different email address.");
        }

        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            bindingResult.rejectValue("confirm", "Matches", "Passwords do not match.");
        }

        if (bindingResult.hasErrors()) {
            return null;
        }

        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPassword);
        return userRepository.save(newUser);
    }

    public User login(LoginUser newLoginObject, BindingResult bindingResult) {
        Optional<User> existingUser = userRepository.findByEmail(newLoginObject.getEmail());
        if (existingUser.isEmpty()) {
            bindingResult.rejectValue("email", "NotFound", "No account with this email address was found. Please create a new user account.");
            return null;
        }

        User user = existingUser.get();

        if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
            bindingResult.rejectValue("password", "Invalid", "Incorrect password.");
        }
        if (bindingResult.hasErrors()) {
            return null;
        }
        else {
            return user;
        }

    }
}