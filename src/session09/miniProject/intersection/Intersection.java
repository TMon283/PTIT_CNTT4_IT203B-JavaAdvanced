package session09.miniProject.intersection;

import session09.miniProject.common.SimClock;
import session09.miniProject.exceptions.CollisionException;
import session09.miniProject.exceptions.TrafficJamException;
import session09.miniProject.control.TrafficControl;
import session09.miniProject.trafficlight.TrafficLightColor;
import session09.miniProject.vehicles.Vehicle;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class Intersection {
    private final String name;
    private final int maxQueueCapacity;
    private final int jamThreshold;
    private final TrafficControl monitor;

    private final BlockingQueue<QueuedVehicle> waitingQueue;
    private final AtomicReference<TrafficLightColor> currentLight = new AtomicReference<>(TrafficLightColor.GREEN);
    private final AtomicBoolean emergencyMode = new AtomicBoolean(false);

    private final Semaphore crossingSlots;
    private final ReentrantLock critical = new ReentrantLock(true);

    private volatile long seq = 0;

    public Intersection(String name, int maxQueueCapacity, int jamThreshold, int maxCrossingVehicles, TrafficControl monitor) {
        this.name = name;
        this.maxQueueCapacity = maxQueueCapacity;
        this.jamThreshold = jamThreshold;
        this.monitor = monitor;
        this.crossingSlots = new Semaphore(maxCrossingVehicles, true);

        this.waitingQueue = new LinkedBlockingQueue<>();
    }

    public void updateLight(TrafficLightColor color) {
        currentLight.set(color);
        if (color == TrafficLightColor.GREEN && !hasWaitingEmergency()) {
            emergencyMode.set(false);
        }
    }

    public void arriveAndWait(Vehicle v, AtomicReference<TrafficLightColor> lastSeenLightRef) {
        int size = waitingQueue.size();
        if (size + 1 > maxQueueCapacity) {
            monitor.recordTrafficJam();
            throw new TrafficJamException("Hàng chờ vượt quá công suất: " + (size + 1) + "/" + maxQueueCapacity);
        }

        long ticket;
        synchronized (this) {
            ticket = ++seq;
        }
        waitingQueue.offer(new QueuedVehicle(v, ticket));

        if (waitingQueue.size() >= jamThreshold) {
            monitor.recordTrafficJam();
            System.out.printf("[Time: %ds] CẢNH BÁO KẸT XE tại %s (queue=%d)%n",
                    SimClock.elapsedSeconds(), name, waitingQueue.size());
        }

        // Chờ đến khi được phép đi
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Vehicle next = peekNextVehicle();
                boolean myTurn = next == v;
                if (!myTurn) {
                    Thread.sleep(80);
                    continue;
                }

                // nếu có xe ưu tiên -> bật emergency mode
                if (v.canPassOnRed()) {
                    emergencyMode.set(true);
                }

                TrafficLightColor light = currentLight.get();
                boolean canGo = emergencyMode.get() ? v.canPassOnRed() : (light == TrafficLightColor.GREEN);
                if (!canGo) {
                    // vẫn đến lượt nhưng đèn đỏ/vàng: đợi tiếp
                    Thread.sleep(120);
                    continue;
                }

                // critical section: vào giao lộ
                if (!crossingSlots.tryAcquire(2, TimeUnit.SECONDS)) {
                    throw new CollisionException("Không lấy được slot qua ngã tư (nguy cơ va chạm)");
                }
                critical.lock();
                try {
                    // Double-check: vẫn là mình ở đầu hàng
                    Vehicle again = peekNextVehicle();
                    if (again != v) {
                        throw new CollisionException("Race condition: xe khác chen vào khi vào giao lộ");
                    }
                    removeVehicle(v);
                    System.out.printf("[Time: %ds] %s #%s đang đi qua ngã tư%s%n",
                            SimClock.elapsedSeconds(), v.displayName(), v.getId(),
                            (v.canPassOnRed() && light == TrafficLightColor.RED) ? " (vượt đèn đỏ - ưu tiên)" : "");
                    // mô phỏng thời gian qua ngã tư
                    Thread.sleep(Math.max(250, 900 - v.getSpeed() * 35L));
                    monitor.recordPassed(v.getType().name());
                } finally {
                    critical.unlock();
                    crossingSlots.release();
                }

                // Nếu vừa là ambulance và không còn ambulance chờ -> tắt emergency mode
                if (v.canPassOnRed() && !hasWaitingEmergency()) {
                    emergencyMode.set(false);
                }
                return;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private Vehicle peekNextVehicle() {
        QueuedVehicle best = null;
        for (QueuedVehicle q : waitingQueue) {
            if (best == null) {
                best = q;
                continue;
            }
            if (QueuedVehicle.COMPARATOR.compare(q, best) < 0) {
                best = q;
            }
        }
        return best == null ? null : best.vehicle;
    }

    private boolean removeVehicle(Vehicle v) {
        for (QueuedVehicle q : waitingQueue) {
            if (q.vehicle == v) {
                return waitingQueue.remove(q);
            }
        }
        return false;
    }

    private boolean hasWaitingEmergency() {
        for (QueuedVehicle q : waitingQueue) {
            if (q.vehicle.canPassOnRed()) return true;
        }
        return false;
    }

    private static final class QueuedVehicle {
        private static final Comparator<QueuedVehicle> COMPARATOR = (a, b) -> {
            // ưu tiên xe có canPassOnRed (ambulance) trước
            boolean ap = a.vehicle.canPassOnRed();
            boolean bp = b.vehicle.canPassOnRed();
            if (ap != bp) return ap ? -1 : 1;
            // sau đó theo ticket (FIFO)
            return Long.compare(a.ticket, b.ticket);
        };

        private final Vehicle vehicle;
        private final long ticket;

        private QueuedVehicle(Vehicle vehicle, long ticket) {
            this.vehicle = vehicle;
            this.ticket = ticket;
        }
    }
}

