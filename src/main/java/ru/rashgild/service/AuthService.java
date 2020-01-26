package ru.rashgild.service;

import javafx.util.Pair;

import ru.rashgild.model.Token;

public interface AuthService {

    Token getToken();

    boolean logout();

    Pair<String, String> getHeader();
}
