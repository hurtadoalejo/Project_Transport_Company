package co.edu.uniquindio.transportCompany.model;

import co.edu.uniquindio.transportCompany.builder.UserBuilder;

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
    public User(String name, int age, double wight, PassengerVehicle vehicleAssociated) {
        this.name = name;
        this.age = age;
        this.wight = wight;
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

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder toBuild(){
        return new UserBuilder()
                .name(name)
                .age(age)
                .wight(wight)
                .vehicleAssociated(vehicleAssociated);
    }

    public void updateUserInVehicle(User newUser){
        if (vehicleAssociated != null) {
            vehicleAssociated.getAssociatedUsersList().replaceAll
                    (user -> user.getName().equals(this.name) ? newUser : user);
        }
    }

    public void updateUserInCompany(User newUser){
        if (vehicleAssociated != null) {
            
        }
    }
}