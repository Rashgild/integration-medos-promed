package ru.rashgild.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rashgild.model.Token;
import ru.rashgild.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Get session id.
     *
     * @return json with session id
     */
    @GetMapping(value = "/get-token", produces = "application/json")
    public ResponseEntity getSessionId() {
        Token token = authService.getToken();

        if (token != null) {
            Map<String, Object> json = new HashMap<>();
            json.put("sessionId", token.getSessId());
            return new ResponseEntity<>(json, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/logout", produces = "application/json")
    public ResponseEntity logout() {

        if (authService.logout()) {
            return new ResponseEntity<>("\"status\":\"ok\"", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
