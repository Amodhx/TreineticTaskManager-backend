package com.example.TreineticTaskManager.service;

import com.example.TreineticTaskManager.dao.UserDAO;
import com.example.TreineticTaskManager.dto.impl.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    public UserDetailsService userDetailsService() {
        return username ->
                new CustomUserDetails(userDAO.findByUsername(username).
                        orElseThrow(() -> new UsernameNotFoundException("User Not Found")));

    }


}
