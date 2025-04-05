package com.example.TreineticTaskManager.dto.impl;
public class JwtAuthResponse {
    private String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public JwtAuthResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
