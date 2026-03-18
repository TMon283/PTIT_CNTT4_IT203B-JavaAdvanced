package session09.miniProject.control;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TrafficControl {
    private final AtomicInteger passedCount = new AtomicInteger();
    private final AtomicInteger trafficJamCount = new AtomicInteger();
    private final Map<String, AtomicInteger> byType = new ConcurrentHashMap<>();

    public void recordPassed(String vehicleType) {
        passedCount.incrementAndGet();
        byType.computeIfAbsent(vehicleType, k -> new AtomicInteger()).incrementAndGet();
    }

    public void recordTrafficJam() {
        trafficJamCount.incrementAndGet();
    }

    public int getPassedCount() {
        return passedCount.get();
    }

    public int getTrafficJamCount() {
        return trafficJamCount.get();
    }

    public Map<String, Integer> snapshotByType() {
        Map<String, Integer> snap = new ConcurrentHashMap<>();
        byType.forEach((k, v) -> snap.put(k, v.get()));
        return snap;
    }
}

