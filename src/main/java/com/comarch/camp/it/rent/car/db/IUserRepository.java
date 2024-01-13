package com.comarch.camp.it.rent.car.db;

import com.comarch.camp.it.rent.car.model.User;

public interface IUserRepository {
    User findByLogin(String login);
    void save();
}
