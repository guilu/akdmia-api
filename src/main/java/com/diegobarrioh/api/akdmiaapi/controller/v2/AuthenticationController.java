package com.diegobarrioh.api.akdmiaapi.controller.v2;

import com.diegobarrioh.api.akdmiaapi.model.JwtToken;
import com.diegobarrioh.api.akdmiaapi.security.autentication.ApiKeyAuthentication;
import com.diegobarrioh.api.akdmiaapi.security.service.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v2/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Endpoint to manage authenticaion")
public class AuthenticationController {

    protected final JwtTokenService jwtTokenService;

    public AuthenticationController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Operation(
            summary = "Authenticate with ApiKey",
            description = "Endpoint to authenticate using an ApiKey string",
            tags = { "Authentication" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtToken.class))
                    ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping("apiKey")
    public JwtToken authentication(@RequestParam("key") String key) {
        if(StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Api key cannot be empty");
        }

        return authenticationProcess(new ApiKeyAuthentication());
    }


    private JwtToken authenticationProcess(Authentication authentication) {
        //comprobar el token apikey con la bbdd o la persistencia que sea

        //si es valido generar un JWT token y devolverlo
        return new JwtToken(jwtTokenService.createJwtToken(authentication));
    }
}
