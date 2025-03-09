package co.edu.uniquindio.transportCompany.builder;

import co.edu.uniquindio.transportCompany.model.CargoVehicle;
import co.edu.uniquindio.transportCompany.model.PassengerVehicle;
import co.edu.uniquindio.transportCompany.model.User;

import java.util.LinkedList;

public class PassengerVehicleBuilder extends VehicleBuilder<PassengerVehicleBuilder>{
    private int maxPassengers;
    private LinkedList<User> associatedUsersList;

    /**
     * Method to obtain the passenger vehicle's max passengers
     * @param maxPassengers New max passengers of the passenger vehicle
     */
    public PassengerVehicleBuilder maxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return self();
    }

    /**
     * Method to modify the passenger vehicle's associated users list
     * @param associatedUsersList New associated users list of the passenger vehicle
     * @return The current PassengerVehicle instance
     */
    public PassengerVehicleBuilder associatedUsersList(LinkedList<User> associatedUsersList) {
        this.associatedUsersList = associatedUsersList;
        return self();
    }

    /**
     * Method to return an object called T
     * @return The current PassengerVehicle instance
     */
    @Override
    protected PassengerVehicleBuilder self() {
        return this;
    }

    /**
     * Method to build one PassengerVehicle instance based on the current attributes
     * @return New PassengerVehicle
     */
    @Override
    public PassengerVehicle build() {
        return new PassengerVehicle(plate, brand, colour, model, proprietor,
                associatedProprietorList, maxPassengers, associatedUsersList);
    }
}
