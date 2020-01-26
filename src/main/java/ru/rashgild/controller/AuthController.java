package ru.rashgild.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.rashgild.model.Token;
import ru.rashgild.service.AuthService;

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
    @RequestMapping(value = "/get-token", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getSessionId() {
        Token token = authService.getToken();

        if (token != null) {
            Map<String, Object> json = new HashMap<>();
            json.put("sessionId", token.getSessId());
            return new ResponseEntity<>(json, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity logout() {

        if (authService.logout()) {
            return new ResponseEntity<>("\"status\":\"ok\"", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
