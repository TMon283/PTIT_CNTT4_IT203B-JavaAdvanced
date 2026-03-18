package session09.miniProject.trafficlight.state;

import session09.miniProject.trafficlight.TrafficLightColor;

public class GreenState implements TrafficLightState {
    @Override
    public TrafficLightColor color() {
        return TrafficLightColor.GREEN;
    }

    @Override
    public TrafficLightState next() {
        return new YellowState();
    }
}

