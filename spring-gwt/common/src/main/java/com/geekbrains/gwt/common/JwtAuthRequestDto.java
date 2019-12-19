package com.geekbrains.gwt.common;

public class JwtAuthRequestDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JwtAuthRequestDto() {
    }

    public JwtAuthRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}