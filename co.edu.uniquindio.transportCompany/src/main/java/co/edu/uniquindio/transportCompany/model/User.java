package co.edu.uniquindio.transportCompany.model;

import java.util.LinkedList;

public class User {
    private String name;
    private int age;
    private double wight;
    private PassengerVehicle vehicleAssociated;

    /**
     * The constructor method for the class User
     * @param name Name of the user to create
     * @param age Age of the user to create
     * @param wight Wight of the user to create
     */
    public User(String name, int age, double wight) {
        this.name = name;
        this.age = age;
        this.wight = wight;
    }

    /**
     * Method to obtain the user's name
     * @return User's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to obtain the user's age
     * @return User's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Method to obtain the user's wight
     * @return user's wight
     */
    public double getWight() {
        return wight;
    }

    /**
     * Method to obtain the user's vehicle associated
     * @return User's vehicle associated
     */
    public PassengerVehicle getVehicleAssociated() {
        return vehicleAssociated;
    }

    /**
     * Method to modify the user's name
     * @param name New username
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to modify the user's age
     * @param age New user age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method to modify the user's wight
     * @param wight New user wight
     */
    public void setWight(double wight) {
        this.wight = wight;
    }

    /**
     * Method to modify the user's vehicle associated
     * @param vehicleAssociated New associated vehicle
     */
    public void setVehicleAssociated(PassengerVehicle vehicleAssociated) {
        this.vehicleAssociated = vehicleAssociated;
    }
}