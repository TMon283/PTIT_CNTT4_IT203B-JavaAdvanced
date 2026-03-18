package session09.miniProject.simulation;

import session09.miniProject.common.SimClock;
import session09.miniProject.intersection.Intersection;
import session09.miniProject.control.TrafficControl;
import session09.miniProject.trafficlight.TrafficLight;
import session09.miniProject.vehicles.Vehicle;
import session09.miniProject.vehicles.VehicleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final TrafficLight trafficLight;
    private final Intersection intersection;
    private final VehicleFactory factory;
    private final TrafficControl monitor;

    public SimulationEngine(TrafficLight trafficLight, Intersection intersection, VehicleFactory factory, TrafficControl monitor) {
        this.trafficLight = trafficLight;
        this.intersection = intersection;
        this.factory = factory;
        this.monitor = monitor;
    }

    public void runForSeconds(int durationSeconds, int maxVehiclesInPool) {
        trafficLight.addObserver(intersection::updateLight);
        trafficLight.runAsDaemon();

        ExecutorService pool = Executors.newFixedThreadPool(maxVehiclesInPool);
        long endAt = System.currentTimeMillis() + durationSeconds * 1000L;

        List<Vehicle> created = new ArrayList<>();

        while (System.currentTimeMillis() < endAt) {
            Vehicle v = factory.createRandomVehicle();
            trafficLight.addObserver(v);
            created.add(v);
            pool.submit(v);

            sleepQuietly(ThreadLocalRandom.current().nextInt(150, 420));
        }

        pool.shutdown();
        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.printf("%n===== BÁO CÁO CUỐI =====%n");
        System.out.printf("Thời gian mô phỏng: %ds%n", SimClock.elapsedSeconds());
        System.out.printf("Số xe qua ngã tư thành công: %d%n", monitor.getPassedCount());
        System.out.printf("Số lần kẹt xe (cảnh báo/overflow): %d%n", monitor.getTrafficJamCount());
        System.out.printf("Thống kê theo loại: %s%n", monitor.snapshotByType());
    }

    private void sleepQuietly(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

