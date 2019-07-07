package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.AuthDao;
import ru.integration.model.Auth;
import ru.integration.model.Token;

@Service("AuthService")
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Override
    public Token getToken() {
        ClientResponse response = authDao.getAuthToken();
        return response.getEntity(Token.class);
    }
}
