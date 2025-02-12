package co.edu.uniquindio.transportCompany;

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
     * @param id Id of the proprietor to create
     */
    public Proprietor(String name, String email, String phoneNumber, String id) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.associatedVehiclesList = new LinkedList<>();
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
     * Method to modify the proprietor's name
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to modify the proprietor's email
     * @param email New email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to modify the proprietor's id
     * @param id New id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method to modify the proprietor's phone number
     * @param phoneNumber New phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method to modify the proprietor's principal vehicle
     * @param principalVehicle New principal vehicle
     */
    public void setPrincipalVehicle(Vehicle principalVehicle) {
        this.principalVehicle = principalVehicle;
    }

    /**
     * Method to modify the proprietor's associated vehicles list
     * @param associatedVehiclesList New associated vehicles list
     */
    public void setAssociatedVehiclesList(LinkedList<Vehicle> associatedVehiclesList) {
        this.associatedVehiclesList = associatedVehiclesList;
    }
}