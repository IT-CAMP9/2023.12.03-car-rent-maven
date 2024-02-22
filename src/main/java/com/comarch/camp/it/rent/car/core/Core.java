package com.comarch.camp.it.rent.car.core;

import com.comarch.camp.it.rent.car.authenticate.Authenticator;
import com.comarch.camp.it.rent.car.authenticate.IAuthenticator;
import com.comarch.camp.it.rent.car.db.*;
import com.comarch.camp.it.rent.car.gui.GUI;
import com.comarch.camp.it.rent.car.gui.IGUI;
import com.comarch.camp.it.rent.car.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Core {
    private final IVehicleRepository baza;
    private final IUserRepository userRepository;
    private final IAuthenticator authenticator;
    private final IGUI gui;

    public Core(IVehicleRepository baza, IUserRepository userRepository,
                IAuthenticator authenticator, IGUI gui) {
        this.baza = baza;
        this.userRepository = userRepository;
        this.authenticator = authenticator;
        this.gui = gui;
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
            /*switch(gui.showMenuAndReadChoose()) {
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
            }*/
            switch(gui.showMenuAndReadChoose()) {
                case "1" -> gui.printVehicles();
                case "2" -> gui.showResult(baza.rentVehicle(gui.readPlate()));
                case "3" -> gui.showResult(baza.returnVehicle(gui.readPlate()));
                case "4" -> {
                    run = false;
                    userRepository.save();
                    baza.save();
                }
                default -> gui.showWrongChoose();
            }
        }
    }
}
