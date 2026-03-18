package session09.miniProject.trafficlight.state;

import session09.miniProject.trafficlight.TrafficLightColor;

public class YellowState implements TrafficLightState {
    @Override
    public TrafficLightColor color() {
        return TrafficLightColor.YELLOW;
    }

    @Override
    public TrafficLightState next() {
        return new RedState();
    }
}

