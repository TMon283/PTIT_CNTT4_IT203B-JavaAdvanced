package session09.miniProject.vehicles;

import session09.miniProject.common.SimClock;
import session09.miniProject.intersection.Intersection;
import session09.miniProject.trafficlight.TrafficLightColor;
import session09.miniProject.trafficlight.TrafficLightObserver;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Vehicle implements Runnable, TrafficLightObserver {
    private final String id;
    private final VehicleType type;
    private final VehiclePriority priority;
    private final int speed;
    private final Intersection intersection;

    private final AtomicReference<TrafficLightColor> lastSeenLight = new AtomicReference<>(TrafficLightColor.GREEN);

    protected Vehicle(String id,
                      VehicleType type,
                      VehiclePriority priority,
                      int speed,
                      Intersection intersection) {
        this.id = id;
        this.type = type;
        this.priority = priority;
        this.speed = speed;
        this.intersection = intersection;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public VehiclePriority getPriority() {
        return priority;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean canPassOnRed() {
        return false;
    }

    @Override
    public void onLightChanged(TrafficLightColor newColor) {
        lastSeenLight.set(newColor);
    }

    @Override
    public final void run() {
        int approachMs = Math.max(200, 1500 - (speed * 50));
        approachMs += ThreadLocalRandom.current().nextInt(0, 250);
        sleepQuietly(approachMs);

        System.out.printf("[Time: %ds] %s #%s tiến đến ngã tư (speed=%d, priority=%s)%n",
                SimClock.elapsedSeconds(), displayName(), id, speed, priority);

        intersection.arriveAndWait(this, lastSeenLight);
    }

    public String displayName() {
        switch (type) {
            case MOTORBIKE:
                return "Xe máy";
            case CAR:
                return "Ô tô";
            case TRUCK:
                return "Xe tải";
            case AMBULANCE:
                return "Xe cứu thương";
            default:
                return "Phương tiện";
        }
    }

    protected void sleepQuietly(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

