package com.picotrake.API.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
    private String token;
}
