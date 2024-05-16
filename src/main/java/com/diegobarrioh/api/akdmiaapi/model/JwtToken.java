package com.diegobarrioh.api.akdmiaapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Token", description = "Authentication JWT Token")
@Data
public class JwtToken {

    @Schema(description = "The authentication token", requiredMode = Schema.RequiredMode.REQUIRED)
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }
}
