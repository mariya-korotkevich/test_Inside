package test_Inside.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test_Inside.models.dto.AuthenticationRequestDTO;
import test_Inside.models.dto.TokenResponseDTO;

@RestController
public class LoginController {

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthenticationRequestDTO authenticationDTO){
        return ResponseEntity.ok(
                new TokenResponseDTO("token + " + authenticationDTO.getName()));
    }
}
