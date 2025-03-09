package co.edu.uniquindio.transportCompany.builder;

import co.edu.uniquindio.transportCompany.model.Proprietor;
import co.edu.uniquindio.transportCompany.model.Vehicle;

import java.util.LinkedList;

public class ProprietorBuilder {
    protected String name, email, phoneNumber, id;
    protected Vehicle principalVehicle;
    protected LinkedList<Vehicle> associatedVehiclesList;

    /**
     * Method to modify the ProprietorBuilder's name
     * @param name New name of the proprietor builder
     * @return The current proprietor builder instance
     */
    public ProprietorBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method to modify the ProprietorBuilder's email
     * @param email New email of the proprietor builder
     * @return The current proprietor builder instance
     */
    public ProprietorBuilder email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Method to modify the ProprietorBuilder's phoneNumber
     * @param phoneNumber New phoneNumber of the proprietor builder
     * @return The current proprietor builder instance
     */
    public ProprietorBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Method to modify the ProprietorBuilder's id
     * @param id New id of the proprietor builder
     * @return The current proprietor builder instance
     */
    public ProprietorBuilder id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Method to modify the ProprietorBuilder's principalVehicle
     * @param principalVehicle New principalVehicle of the proprietor builder
     * @return The current proprietor builder instance
     */
    public ProprietorBuilder principalVehicle(Vehicle principalVehicle) {
        this.principalVehicle = principalVehicle;
        return this;
    }

    /**
     * Method to modify the ProprietorBuilder's associated vehicles list
     * @param associatedVehiclesList New associated vehicles list of the proprietor builder
     * @return The current proprietor builder instance
     */
    public ProprietorBuilder associatedVehiclesList(LinkedList<Vehicle> associatedVehiclesList) {
        this.associatedVehiclesList = associatedVehiclesList;
        return this;
    }

    /**
     * Method to build one proprietor instance based on the current attributes
     * @return new Proprietor
     */
    public Proprietor build(){
        return new Proprietor(name, email, phoneNumber, id, principalVehicle, associatedVehiclesList);
    }
}