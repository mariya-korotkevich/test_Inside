package test_Inside.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test_Inside.models.dto.AuthenticationRequestDTO;
import test_Inside.models.dto.TokenResponseDTO;
import test_Inside.security.JwtUtils;

@RestController
public class LoginController {

    private final JwtUtils jwtUtils;

    public LoginController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthenticationRequestDTO authenticationDTO){

        String jwtToken = jwtUtils.generateJwtToken(authenticationDTO.getName());

        return ResponseEntity.ok(
                new TokenResponseDTO(jwtToken));
    }
}
