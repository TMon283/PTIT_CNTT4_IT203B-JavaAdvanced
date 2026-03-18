package session09.miniProject;

import session09.miniProject.intersection.Intersection;
import session09.miniProject.control.TrafficControl;
import session09.miniProject.simulation.SimulationEngine;
import session09.miniProject.trafficlight.TrafficLight;
import session09.miniProject.vehicles.VehicleFactory;

public class Main {
    public static void main(String[] args) {
        TrafficControl monitor = new TrafficControl();
        Intersection intersection = new Intersection(
                "Ngã tư A",
                200,
                25,
                1,
                monitor
        );

        TrafficLight light = new TrafficLight(
                6, // green
                2, // yellow
                6  // red
        );

        VehicleFactory factory = new VehicleFactory(intersection);
        SimulationEngine engine = new SimulationEngine(light, intersection, factory, monitor);

        engine.runForSeconds(25, 60);
    }
}

