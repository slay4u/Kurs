package com.company.kurs.service;

import com.company.kurs.login.MyUserDetails;
import com.company.kurs.login.User;
import com.company.kurs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.EntityNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if(user == null){
            throw new EntityNotFoundException("Could not find user!");
        }
        return new MyUserDetails(user);
    }
}
