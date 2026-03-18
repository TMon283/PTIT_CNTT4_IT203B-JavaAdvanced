package session09.miniProject.common;

public final class SimClock {
    private static final long START_MS = System.currentTimeMillis();

    private SimClock() {
    }

    public static long elapsedSeconds() {
        return (System.currentTimeMillis() - START_MS) / 1000;
    }
}

