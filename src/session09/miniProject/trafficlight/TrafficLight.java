package session09.miniProject.trafficlight;

import session09.miniProject.common.SimClock;
import session09.miniProject.trafficlight.state.GreenState;
import session09.miniProject.trafficlight.state.TrafficLightState;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrafficLight {
    private final int greenSeconds;
    private final int yellowSeconds;
    private final int redSeconds;

    private final List<TrafficLightObserver> observers = new CopyOnWriteArrayList<>();
    private volatile TrafficLightState state = new GreenState();

    public TrafficLight(int greenSeconds, int yellowSeconds, int redSeconds) {
        if (greenSeconds <= 0 || yellowSeconds <= 0 || redSeconds <= 0) {
            throw new IllegalArgumentException("Chu kỳ đèn phải > 0");
        }
        this.greenSeconds = greenSeconds;
        this.yellowSeconds = yellowSeconds;
        this.redSeconds = redSeconds;
    }

    public TrafficLightColor getColor() {
        return state.color();
    }

    public void addObserver(TrafficLightObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TrafficLightObserver observer) {
        observers.remove(observer);
    }

    public void runAsDaemon() {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    sleepForCurrentState();
                    advanceState();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "TrafficLight-Daemon");
        t.setDaemon(true);
        t.start();
    }

    /**
     * Dùng cho unit test: chuyển trạng thái ngay lập tức (không sleep).
     */
    public void advanceState() {
        state = state.next();
        System.out.printf("[Time: %ds] Đèn chuyển sang %s%n", SimClock.elapsedSeconds(), state.color());
        notifyObservers(state.color());
    }

    private void notifyObservers(TrafficLightColor newColor) {
        for (TrafficLightObserver o : observers) {
            o.onLightChanged(newColor);
        }
    }

    private void sleepForCurrentState() throws InterruptedException {
        switch (state.color()) {
            case GREEN:
                Thread.sleep(greenSeconds * 1000L);
                break;
            case YELLOW:
                Thread.sleep(yellowSeconds * 1000L);
                break;
            case RED:
                Thread.sleep(redSeconds * 1000L);
                break;
            default:
                Thread.sleep(1000L);
        }
    }
}

