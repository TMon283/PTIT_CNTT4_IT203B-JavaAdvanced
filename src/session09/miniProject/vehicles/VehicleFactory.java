package session09.miniProject.vehicles;

import session09.miniProject.intersection.Intersection;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class VehicleFactory {
    private final Intersection intersection;
    private final Map<VehicleType, AtomicInteger> seq = new EnumMap<>(VehicleType.class);

    public VehicleFactory(Intersection intersection) {
        this.intersection = intersection;
        for (VehicleType t : VehicleType.values()) {
            seq.put(t, new AtomicInteger());
        }
    }

    public Vehicle createRandomVehicle() {
        VehicleType type = randomTypeWeighted();
        return createVehicle(type);
    }

    public Vehicle createVehicle(VehicleType type) {
        int no = seq.get(type).incrementAndGet();
        String id = String.format("%02d", no);

        int speed = randomSpeed(type);
        switch (type) {
            case AMBULANCE:
                return new PriorityVehicle(id, type, speed, intersection);
            case MOTORBIKE:
            case CAR:
            case TRUCK:
            default:
                return new StandardVehicle(id, type, speed, intersection);
        }
    }

    private VehicleType randomTypeWeighted() {
        int r = ThreadLocalRandom.current().nextInt(100);
        if (r < 8) return VehicleType.AMBULANCE;
        if (r < 50) return VehicleType.MOTORBIKE;
        if (r < 85) return VehicleType.CAR;
        return VehicleType.TRUCK;
    }

    private int randomSpeed(VehicleType type) {
        switch (type) {
            case MOTORBIKE:
                return ThreadLocalRandom.current().nextInt(12, 20);
            case CAR:
                return ThreadLocalRandom.current().nextInt(10, 18);
            case TRUCK:
                return ThreadLocalRandom.current().nextInt(6, 12);
            case AMBULANCE:
                return ThreadLocalRandom.current().nextInt(14, 20);
            default:
                return ThreadLocalRandom.current().nextInt(8, 16);
        }
    }
}

