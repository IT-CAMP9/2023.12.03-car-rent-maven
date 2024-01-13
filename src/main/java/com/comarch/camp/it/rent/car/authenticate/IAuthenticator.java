package com.comarch.camp.it.rent.car.authenticate;

public interface IAuthenticator {
    boolean authenticate(String login, String password);
}
