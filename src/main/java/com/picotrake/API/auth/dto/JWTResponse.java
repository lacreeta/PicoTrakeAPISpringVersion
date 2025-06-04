package com.picotrake.API.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
    private String token;
}
