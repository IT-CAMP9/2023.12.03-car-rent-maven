package com.comarch.camp.it.rent.car.db;

import com.comarch.camp.it.rent.car.model.Vehicle;

import java.util.Collection;

public interface IVehicleRepository {
    boolean rentVehicle(String plate);
    boolean returnVehicle(String plate);
    Collection<Vehicle> getVehicles();
    void save();
}
