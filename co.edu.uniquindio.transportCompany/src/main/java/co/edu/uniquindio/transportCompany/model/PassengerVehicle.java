package co.edu.uniquindio.transportCompany.model;

import java.util.LinkedList;

public class PassengerVehicle extends Vehicle{
    private int maxPassengers;
    private LinkedList<User> associatedUsersList;

    /**
     * The constructor method for the class passenger vehicle
     * @param plate Plate of the passenger vehicle to create
     * @param brand Brand of the passenger vehicle to create
     * @param colour Colour of the passenger vehicle to create
     * @param model Model of the passenger vehicle to create
     * @param proprietor Proprietor of the passenger vehicle to create
     * @param associatedProprietorList Associated proprietor list of the passenger vehicle to create
     * @param maxPassengers Max passenger of the passenger vehicle to create
     * @param associatedUsersList Associated users list of the passenger vehicle to create
     */
    public PassengerVehicle(String plate, String brand, String colour, int model, Proprietor proprietor,
                            LinkedList<Proprietor> associatedProprietorList, int maxPassengers,
                            LinkedList<User> associatedUsersList) {
        super(plate, brand, colour, model, proprietor, associatedProprietorList);
        this.maxPassengers = maxPassengers;
        this.associatedUsersList = associatedUsersList;
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
}