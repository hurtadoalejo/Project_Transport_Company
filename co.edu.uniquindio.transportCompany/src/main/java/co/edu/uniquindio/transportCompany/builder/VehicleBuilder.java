package co.edu.uniquindio.transportCompany.builder;

import co.edu.uniquindio.transportCompany.model.Proprietor;
import co.edu.uniquindio.transportCompany.model.Vehicle;

import java.util.LinkedList;

public class VehicleBuilder <T extends VehicleBuilder<T>>{
    protected String plate, brand, colour;
    protected int model;
    protected Proprietor proprietor;
    protected LinkedList<Proprietor> associatedProprietorList;

    /**
     * Method to modify the vehicle's plate
     * @param plate New plate of the vehicle
     * @return The current VehicleBuilder instance
     */
    public T plate(String plate) {
        this.plate = plate;
        return self();
    }

    /**
     * Method to modify the vehicle's brand
     * @param brand New brand of the vehicle
     * @return The current VehicleBuilder instance
     */
    public T brand(String brand) {
        this.brand = brand;
        return self();
    }

    /**
     * Method to modify the vehicle's colour
     * @param colour New colour of the vehicle
     * @return The current VehicleBuilder instance
     */
    public T colour(String colour) {
        this.colour = colour;
        return self();
    }

    /**
     * Method to modify the vehicle's model
     * @param model New model of the vehicle
     * @return The current VehicleBuilder instance
     */
    public T model(int model) {
        this.model = model;
        return self();
    }

    /**
     * Method to modify the vehicle's proprietor
     * @param proprietor New proprietor of the vehicle
     * @return The current VehicleBuilder instance
     */
    public T proprietor(Proprietor proprietor) {
        this.proprietor = proprietor;
        return self();
    }

    /**
     * Method to modify the vehicle's associated proprietor List
     * @param associatedProprietorList New associatedProprietorList of the vehicle
     * @return The current VehicleBuilder instance
     */
    public T associatedProprietorList(LinkedList<Proprietor> associatedProprietorList) {
        this.associatedProprietorList = associatedProprietorList;
        return self();
    }

    /**
     * Method to return an object called T
     * @return The current VehicleBuilder instance
     */
    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    /**
     * Method to build one Vehicle instance based on the current attributes
     * @return New vehicle
     */
    public Vehicle build() {
        return new Vehicle(plate, brand, colour, model, proprietor, associatedProprietorList);
    }
}