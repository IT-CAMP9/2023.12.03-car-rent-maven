package com.comarch.camp.it.rent.car.core;

import com.comarch.camp.it.rent.car.authenticate.Authenticator;
import com.comarch.camp.it.rent.car.authenticate.IAuthenticator;
import com.comarch.camp.it.rent.car.db.*;
import com.comarch.camp.it.rent.car.gui.GUI;
import com.comarch.camp.it.rent.car.gui.IGUI;
import com.comarch.camp.it.rent.car.model.User;

public class Core {
    final IVehicleRepository baza = VehicleRepository.getInstance();
    final IUserRepository userRepository = UserRepository.getInstance();
    final IAuthenticator authenticator = Authenticator.getInstance();
    final IGUI gui = GUI.getInstance();

    private static final Core instance = new Core();

    private Core() {

    }

    public void start() {
        if(authenticate()) {
            run();
        }
    }

    private boolean authenticate() {
        for(int i = 0; i < 3; i++) {
            User user = gui.readLoginData();
            boolean authResult = authenticator.authenticate(user.getLogin(), user.getPassword());
            if(authResult) {
                System.out.println("Logged !!");
                return true;
            }
            System.out.println("Incorrect login data !!");
        }
        return false;
    }

    private void run() {
        boolean run = true;
        while(run) {
            switch(gui.showMenuAndReadChoose()) {
                case "1":
                    gui.printVehicles();
                    break;
                case "2":
                    gui.showResult(baza.rentVehicle(gui.readPlate()));
                    break;
                case "3":
                    gui.showResult(baza.returnVehicle(gui.readPlate()));
                    break;
                case "4":
                    run = false;
                    userRepository.save();
                    baza.save();
                    break;
                default:
                    gui.showWrongChoose();
                    break;
            }
        }
    }

    public static Core getInstance() {
        return instance;
    }
}
