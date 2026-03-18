package session09.miniProject.vehicles;

import session09.miniProject.intersection.Intersection;

public class StandardVehicle extends Vehicle {
    public StandardVehicle(String id, VehicleType type, int speed, Intersection intersection) {
        super(id, type, VehiclePriority.NORMAL, speed, intersection);
    }
}

