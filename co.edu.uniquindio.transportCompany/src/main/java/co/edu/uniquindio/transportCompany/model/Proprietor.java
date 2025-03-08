package co.edu.uniquindio.transportCompany.model;

import co.edu.uniquindio.transportCompany.builder.ProprietorBuilder;

import java.util.LinkedList;

public class Proprietor {
    private String name, email, phoneNumber, id;
    private Vehicle principalVehicle;
    private LinkedList<Vehicle> associatedVehiclesList;

    /**
     * The constructor method for the class Proprietor
     * @param name Name of the proprietor to create
     * @param email Email of the proprietor to create
     * @param phoneNumber Phone number of the proprietor to create
     * @param id ID of the proprietor to create
     */
    public Proprietor(String name, String email, String phoneNumber, String id, Vehicle principalVehicle,
                      LinkedList<Vehicle> associatedVehiclesList) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.principalVehicle = principalVehicle;
        this.associatedVehiclesList = associatedVehiclesList;
    }

    /**
     * Method to obtain the proprietor's name
     * @return Proprietor's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to obtain the proprietor's email
     * @return Proprietor's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to obtain the proprietor's id
     * @return Proprietor's id
     */
    public String getId() {
        return id;
    }

    /**
     * Method to obtain the proprietor's phone number
     * @return Proprietor's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method to obtain the proprietor's vehicle
     * @return Proprietor's vehicle
     */
    public Vehicle getPrincipalVehicle() {
        return principalVehicle;
    }

    /**
     * Method to obtain the proprietor's associated vehicles list
     * @return Proprietor's associated vehicles list
     */
    public LinkedList<Vehicle> getAssociatedVehiclesList() {
        return associatedVehiclesList;
    }

    /**
     * Method to create a proprietor builder instance
     * @return Proprietor builder instance
     */
    public static ProprietorBuilder builder(){
        return new ProprietorBuilder();
    }
}