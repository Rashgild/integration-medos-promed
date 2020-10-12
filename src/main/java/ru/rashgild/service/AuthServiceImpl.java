package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rashgild.dao.AuthDao;
import ru.rashgild.model.Token;

import java.util.AbstractMap;
import java.util.Map;

@Service("AuthService")
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    private Token token;

    @Override
    public Token getToken() {
        if (token == null) {
            ClientResponse response = authDao.getAuthToken();
            if (response.getStatus() == HttpStatus.OK.value()) {
                token = response.getEntity(Token.class);
            }
        }
        return token;
    }

    @Override
    public Map.Entry<String, String> getHeader() {
        return new AbstractMap.SimpleEntry<>("Cookie", "PHPSESSID=" + getSessionId());
    }

    @Override
    public boolean logout() {
        ClientResponse response = authDao.logout();
        if (response.getStatus() == HttpStatus.OK.value()) {
            log.info(response.getEntity(String.class));
            this.token = null;
            return true;
        }
        return false;
    }
}
