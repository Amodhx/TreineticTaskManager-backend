package com.example.TreineticTaskManager.service;

import com.example.TreineticTaskManager.dao.UserDAO;
import com.example.TreineticTaskManager.dto.impl.CustomUserDetails;
import com.example.TreineticTaskManager.dto.impl.JwtAuthResponse;
import com.example.TreineticTaskManager.dto.impl.UserDTO;
import com.example.TreineticTaskManager.entity.impl.UserEntity;
import com.example.TreineticTaskManager.util.GenerateId;
import com.example.TreineticTaskManager.util.MappingObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GenerateId generateId;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MappingObjects mappingObjects;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public JwtAuthResponse signUp(UserDTO user) {
        UserDTO userDTO = new UserDTO(generateId.generateUserId(),user.getUsername(),passwordEncoder.encode(user.getPassword()));
        UserEntity save = userDAO.save(mappingObjects.toUserEntity(userDTO));
        UserDetails userDetails = new CustomUserDetails(save);
        String generateToken = jwtService.generateToken(userDetails);
        return new JwtAuthResponse(generateToken);
    }

    public JwtAuthResponse signIn(UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );
        UserEntity user = userDAO.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserDetails userDetails = new CustomUserDetails(user);
        var generateToken = jwtService.generateToken(userDetails);
        return new JwtAuthResponse(generateToken);
    }

}
