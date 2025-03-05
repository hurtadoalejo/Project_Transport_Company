package co.edu.uniquindio.transportCompany.builder;

import co.edu.uniquindio.transportCompany.model.PassengerVehicle;
import co.edu.uniquindio.transportCompany.model.User;

public class UserBuilder {
    protected String name;
    protected int age;
    protected double wight;
    protected PassengerVehicle vehicleAssociated;

    public UserBuilder name(String name){
        this.name = name;
        return this;
    }

    public UserBuilder age(int age){
        this.age = age;
        return this;
    }

    public UserBuilder wight(double wight){
        this.wight = wight;
        return this;
    }

    public UserBuilder vehicleAssociated(PassengerVehicle vehicleAssociated){
        this.vehicleAssociated = vehicleAssociated;
        return this;
    }

    public User build(){
        return new User(name, age, wight, vehicleAssociated);
    }
}
