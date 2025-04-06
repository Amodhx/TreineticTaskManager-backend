package com.example.TreineticTaskManager.controller;

import com.example.TreineticTaskManager.dto.impl.JwtAuthResponse;
import com.example.TreineticTaskManager.dto.impl.UserDTO;
import com.example.TreineticTaskManager.exceptions.UserNotFoundException;
import com.example.TreineticTaskManager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody UserDTO userDTO){
        try {
            JwtAuthResponse jwtAuthResponse = authService.signIn(userDTO);
            System.out.println(jwtAuthResponse.getEmail());
            return new ResponseEntity<>(jwtAuthResponse,HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping(path = "/signUp")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody UserDTO userDTO){
        try {
            JwtAuthResponse jwtAuthResponse = authService.signUp(userDTO);
            return new ResponseEntity<>(jwtAuthResponse,HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody UserDTO userDTO){
        try {
            authService.changePassword(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
