package co.edu.uniquindio.transportCompany.model;

import co.edu.uniquindio.transportCompany.builder.UserBuilder;

public class User {
    private String name;
    private int age;
    private double weight;
    private PassengerVehicle vehicleAssociated;

    /**
     * The constructor method for the class User
     * @param name Name of the user to create
     * @param age Age of the user to create
     * @param weight Weight of the user to create
     * @param vehicleAssociated Vehicle associated of the user to create
     */
    public User(String name, int age, double weight, PassengerVehicle vehicleAssociated) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.vehicleAssociated = vehicleAssociated;
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
     * Method to obtain the user's weight
     * @return user's weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Method to obtain the user's vehicle associated
     * @return User's vehicle associated
     */
    public PassengerVehicle getVehicleAssociated() {
        return vehicleAssociated;
    }

    /**
     * Method to create a user builder instance
     * @return User builder instance
     */
    public static UserBuilder builder(){
        return new UserBuilder();
    }
}