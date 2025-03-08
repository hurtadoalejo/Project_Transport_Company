package co.edu.uniquindio.transportCompany.builder;

import co.edu.uniquindio.transportCompany.model.PassengerVehicle;
import co.edu.uniquindio.transportCompany.model.User;

public class UserBuilder {
    protected String name;
    protected int age;
    protected double weight;
    protected PassengerVehicle vehicleAssociated;

    /**
     * Method to modify the UserBuilder's name
     * @param name New name of the user builder
     * @return The current UserBuilder instance
     */
    public UserBuilder name(String name){
        this.name = name;
        return this;
    }

    /**
     * Method to modify the UserBuilder's age
     * @param age New age of the user builder
     * @return The current UserBuilder instance
     */
    public UserBuilder age(int age){
        this.age = age;
        return this;
    }

    /**
     * Method to modify the UserBuilder's wight
     * @param weight New weight of the user builder
     * @return The current UserBuilder instance
     */
    public UserBuilder weight(double weight){
        this.weight = weight;
        return this;
    }

    /**
     * Method to modify the UserBuilder's vehicle associated
     * @param vehicleAssociated New vehicle associated of the user builder
     * @return The current UserBuilder instance
     */
    public UserBuilder vehicleAssociated(PassengerVehicle vehicleAssociated){
        this.vehicleAssociated = vehicleAssociated;
        return this;
    }

    /**
     * Method to build one user instance based on the current attributes
     * @return new User
     */
    public User build(){
        return new User(name, age, weight, vehicleAssociated);
    }
}
