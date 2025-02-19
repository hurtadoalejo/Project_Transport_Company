package co.edu.uniquindio.transportCompany.model;

import java.util.LinkedList;

public class PassengerVehicle extends Vehicle{
    private int maxPassengers;
    private LinkedList<User> associatedUsersList;

    /**
     * The constructor method for the class PassengerVehicle
     * @param plate Plate of the vehicle to create
     * @param brand Brand of the vehicle to create
     * @param colour Colour of the vehicle to create
     * @param model Model of the vehicle to create
     * @param proprietor Proprietor of the vehicle to create
     * @param maxPassengers Max passengers of the vehicle to create
     */
    public PassengerVehicle(String plate, String brand, String colour, int model, Proprietor proprietor,
                            int maxPassengers) {
        super(plate, brand, colour, model, proprietor);
        this.maxPassengers = maxPassengers;
        this.associatedUsersList = new LinkedList<>();
    }

    /**
     * Method to obtain the vehicle's max passengers
     * @return Vehicle's max passengers
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * Method to obtain the vehicle's associated users list
     * @return Vehicle's associated users list
     */
    public LinkedList<User> getAssociatedUsersList() {
        return associatedUsersList;
    }

    /**
     * Method to obtain the vehicle's max passengers
     * @param maxPassengers New max passengers of the vehicle
     */
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    /**
     * Method to modify the vehicle's associated users list
     * @param associatedUsersList New associated users list
     */
    public void setAssociatedUsersList(LinkedList<User> associatedUsersList) {
        this.associatedUsersList = associatedUsersList;
    }
}