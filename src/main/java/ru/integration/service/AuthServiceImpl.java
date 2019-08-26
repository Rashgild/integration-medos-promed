package ru.integration.service;

import com.sun.jersey.api.client.ClientResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.AuthDao;
import ru.integration.model.Token;

@Service("AuthService")
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    private Token token;

    @Override
    public Token getToken() {
        if (token == null) {
            ClientResponse response = authDao.getAuthToken();
            token = response.getEntity(Token.class);
            return token;
        } else {
            return token;
        }
    }

    @Override
    public void logout() {
        ClientResponse response = authDao.logout();
        if (response.getStatus() == HttpStatus.OK.value()) {
            this.token = null;
        }
    }
}
