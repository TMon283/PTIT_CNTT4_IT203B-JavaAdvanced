package session09.miniProject.vehicles;

public enum VehiclePriority {
    NORMAL(1),
    HIGH(2);

    private final int level;

    VehiclePriority(int level) {
        this.level = level;
    }

    public int level() {
        return level;
    }
}

