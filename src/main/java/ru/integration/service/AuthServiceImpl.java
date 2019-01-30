package ru.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.integration.dao.AuthDao;
import ru.integration.model.Auth;

@Service("AuthService")
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Override
    public Auth getSessionId() {
        return authDao.getSessionId();
    }

}
