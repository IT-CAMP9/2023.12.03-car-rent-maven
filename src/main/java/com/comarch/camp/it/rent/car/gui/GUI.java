package com.comarch.camp.it.rent.car.gui;

import com.comarch.camp.it.rent.car.authenticate.Authenticator;
import com.comarch.camp.it.rent.car.db.IVehicleRepository;
import com.comarch.camp.it.rent.car.db.VehicleRepository;
import com.comarch.camp.it.rent.car.model.LuxuryCar;
import com.comarch.camp.it.rent.car.model.User;
import com.comarch.camp.it.rent.car.model.Vehicle;

import java.util.Collection;
import java.util.Scanner;

public class GUI implements IGUI {
    private final Scanner scanner = new Scanner(System.in);
    private final IVehicleRepository vehicleRepository = VehicleRepository.getInstance();
    private static final GUI instance = new GUI();

    private GUI() {}

    @Override
    public String showMenuAndReadChoose() {
        System.out.println("1. List cars");
        System.out.println("2. Rent car");
        System.out.println("3. Return car");
        System.out.println("4. Exit");
        return scanner.nextLine();
    }

    @Override
    public void printVehicles() {
        for(Vehicle vehicle : vehicleRepository.getVehicles()) {
            if(vehicle instanceof LuxuryCar && !"ADMIN".equals(Authenticator.loggedUserRole)) {
                continue;
            }
            System.out.println(vehicle);
        }
    }

    @Override
    public String readPlate() {
        System.out.println("Enter plate:");
        return scanner.nextLine();
    }

    @Override
    public void showResult(boolean rentResult) {
        if(rentResult) {
            System.out.println("Success !!");
        } else {
            System.out.println("Error !!");
        }
    }

    @Override
    public void showWrongChoose() {
        System.out.println("Wrong choose !!");
    }

    @Override
    public User readLoginData() {
        System.out.println("Login:");
        String login = scanner.nextLine();
        System.out.println("Password:");
        return new User(login, scanner.nextLine());
    }

    public static GUI getInstance() {
        return instance;
    }
}
