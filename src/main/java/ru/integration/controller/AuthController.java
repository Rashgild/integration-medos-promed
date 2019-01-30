package ru.integration.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.integration.dao.AuthDao;
import ru.integration.model.Auth;
import ru.integration.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthDao authDao;

    @RequestMapping(value = "/session-id", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object>  sessionId() {
        Map<String, Object> json = new HashMap<>();
        json.put("sessionId", authService.getSessionId());
        return json;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public String test() {
        Auth entity= new Auth();
        entity.setPhpSessId("123");
        authDao.save(entity);
        return String.valueOf("123");
    }
}
