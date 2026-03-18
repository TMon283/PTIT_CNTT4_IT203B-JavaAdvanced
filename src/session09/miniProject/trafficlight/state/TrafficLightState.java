package session09.miniProject.trafficlight.state;

import session09.miniProject.trafficlight.TrafficLightColor;

public interface TrafficLightState {
    TrafficLightColor color();

    TrafficLightState next();
}

