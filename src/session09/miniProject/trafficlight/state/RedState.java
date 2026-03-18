package session09.miniProject.trafficlight.state;

import session09.miniProject.trafficlight.TrafficLightColor;

public class RedState implements TrafficLightState {
    @Override
    public TrafficLightColor color() {
        return TrafficLightColor.RED;
    }

    @Override
    public TrafficLightState next() {
        return new GreenState();
    }
}

