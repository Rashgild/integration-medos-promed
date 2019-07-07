package ru.integration.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.integration.model.Token;
import ru.integration.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Get session id.
     *
     * @return json with session id
     */
    @RequestMapping(value = "/session-id", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getSessionId() {
        Token token = authService.getToken();
        System.out.println(token.getErrorCode());
        if (token != null) {
            Map<String, Object> json = new HashMap<>();
            json.put("sessionId", token.getSessId());
            return new ResponseEntity<>(json, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
