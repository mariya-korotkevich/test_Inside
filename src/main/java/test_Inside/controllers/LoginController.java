package test_Inside.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test_Inside.models.dto.AuthenticationRequestDTO;
import test_Inside.models.dto.TokenResponseDTO;
import test_Inside.security.jwt.JwtUtils;

@RestController
public class LoginController {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthenticationRequestDTO authenticationDTO){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getName(),
                        authenticationDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtils.generateJwtToken(authenticationDTO.getName());

        return ResponseEntity.ok(new TokenResponseDTO(jwtToken));
    }
}
