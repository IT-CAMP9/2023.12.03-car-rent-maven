package com.comarch.camp.it.rent.car.gui;

import com.comarch.camp.it.rent.car.model.User;

public interface IGUI {
    String showMenuAndReadChoose();
    void printVehicles();
    String readPlate();
    void showResult(boolean rentResult);
    void showWrongChoose();
    User readLoginData();
}
