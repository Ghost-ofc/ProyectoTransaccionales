package com.Vitalife.Vitalife.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class LoginResponse {
    String token;

    public LoginResponse() {
    }
    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
