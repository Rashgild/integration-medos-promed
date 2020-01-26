package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import javafx.util.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.rashgild.dao.AuthDao;
import ru.rashgild.model.Token;

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
    public Pair<String, String> getHeader() {
        return new Pair<>("Cookie", "PHPSESSID=" + this.getToken().getSessId());
    }

    @Override
    public boolean logout() {
        ClientResponse response = authDao.logout();
        if (response.getStatus() == HttpStatus.OK.value()) {
            System.out.println(response.getEntity(String.class));
            this.token = null;
            return true;
        }
        return false;
    }
}
