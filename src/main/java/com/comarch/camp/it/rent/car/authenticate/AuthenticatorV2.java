package com.comarch.camp.it.rent.car.authenticate;

import org.springframework.stereotype.Component;

public class AuthenticatorV2 implements IAuthenticator {
    @Override
    public boolean authenticate(String login, String password) {
        return true;
    }
}
