package com.empresax.security.service.impl;

import com.empresax.security.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailtsServiceImpl implements UserDetailsService {

    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailtsImpl(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found")));
    }
}
