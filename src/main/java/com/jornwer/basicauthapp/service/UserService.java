package com.jornwer.basicauthapp.service;

import com.jornwer.basicauthapp.dto.LoginDTO;
import com.jornwer.basicauthapp.dto.UserDTO;
import com.jornwer.basicauthapp.model.Status;
import com.jornwer.basicauthapp.model.User;
import com.jornwer.basicauthapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public boolean registerUser(UserDTO dto){
        if (userRepository.findByEmailOrUsername(dto.getEmail(), dto.getUsername()).isPresent()){
            return false;
        }
        User user = new User(dto, passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        loginUser(dto.getEmail(), dto.getPassword());
        return true;
    }

    public boolean login(LoginDTO dto) {
        try {
            loginUser(dto.getEmail(), dto.getPassword());
            return true;
        } catch (AuthenticationException e){
            return false;
        }
    }

    private void loginUser(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void blockUsers(List<Integer> list) {
        for (Integer i : list) {
            Optional<User> user = userRepository.findById(i.longValue());
            if (user.isPresent()) {
                User u = user.get();
                u.setStatus(Status.BANNED);
                userRepository.save(u);
            }
        }
    }

    public void unblockUsers(List<Integer> list) {
        for (Integer i : list) {
            Optional<User> user = userRepository.findById(i.longValue());
            if (user.isPresent()) {
                User u = user.get();
                u.setStatus(Status.ACTIVE);
                userRepository.save(u);
            }
        }
    }

    public void deleteUsers(List<Integer> list) {
        for (Integer i : list) {
            userRepository.deleteById(i.longValue());
        }
    }
}
