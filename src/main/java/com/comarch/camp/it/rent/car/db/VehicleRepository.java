package com.comarch.camp.it.rent.car.db;

import com.comarch.camp.it.rent.car.core.Constants;
import com.comarch.camp.it.rent.car.authenticate.Authenticator;
import com.comarch.camp.it.rent.car.model.*;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class VehicleRepository implements IVehicleRepository {
    private final HashMap<String, Vehicle> vehicles = new HashMap<>();
    private static final VehicleRepository instance = new VehicleRepository();

    private VehicleRepository() {
        try(BufferedReader reader =
                    new BufferedReader(new FileReader(Constants.VEHICLES_FILE))) {
            String lineFromFile;
            while((lineFromFile = reader.readLine()) != null) {
                String[] vehicleParts = lineFromFile.split(";");
                Vehicle vehicle = null;
                switch(vehicleParts[0]) {
                    case "Car":
                        vehicle = new Car(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5]
                        );
                        break;
                    case "Bus":
                        vehicle = new Bus(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5],
                                Integer.parseInt(vehicleParts[7])
                        );
                        break;
                    case "LuxuryCar":
                        vehicle = new LuxuryCar(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5]
                        );
                        break;
                    case "Truck":
                        vehicle = new Truck(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5],
                                Integer.parseInt(vehicleParts[7])
                        );
                        break;
                    case "Motorcycle":
                        vehicle = new Motorcycle(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5],
                                Boolean.parseBoolean(vehicleParts[7])
                        );
                        break;
                }
                vehicle.setRent(Boolean.parseBoolean(vehicleParts[6]));
                this.vehicles.put(vehicle.getPlate(), vehicle);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie ma, zepsulo sie !!");
        } catch (IOException e) {
            System.out.println("Nie da sie pliku odczytać !!");
        }
    }

    @Override
    public boolean rentVehicle(String plate) {
        Vehicle vehicle = this.vehicles.get(plate);
        if(vehicle instanceof LuxuryCar &&
                !"ADMIN".equals(Authenticator.loggedUserRole)) {
            return false;
        }
        if(vehicle != null && !vehicle.isRent()) {
            vehicle.setRent(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnVehicle(String plate) {
        Vehicle vehicle = this.vehicles.get(plate);
        if(vehicle instanceof LuxuryCar &&
                !"ADMIN".equals(Authenticator.loggedUserRole)) {
            return false;
        }
        if(vehicle != null && vehicle.isRent()) {
            vehicle.setRent(false);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Vehicle> getVehicles() {
        return this.vehicles.values();
    }

    @Override
    public void save() {
        try(BufferedWriter writer =
                    new BufferedWriter(new FileWriter(Constants.VEHICLES_FILE))) {
            boolean first = true;
            for(Vehicle vehicle : this.vehicles.values()) {
                if(!first) {
                    writer.newLine();
                }
                first = false;
                writer.write(vehicle.convertToCSVString());
            }
        } catch (IOException e) {
            System.out.println("Vehicles file writing error !");
        }
    }

    public static VehicleRepository getInstance() {
        return instance;
    }
}
