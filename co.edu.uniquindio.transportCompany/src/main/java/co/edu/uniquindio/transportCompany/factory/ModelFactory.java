package co.edu.uniquindio.transportCompany.factory;

import co.edu.uniquindio.transportCompany.model.*;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private TransportCompany transportCompany;

    /**
     * Method constructor of the class ModelFactory
     */
    private ModelFactory(){
        initializeData();
    }

    /**
     * Method to get the only model factory instance
     * @return The only model factory instance
     */
    public static ModelFactory getInstance(){
        if (modelFactory == null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    /**
     * Method to get the only transport company instance
     * @return The only transport company instance
     */
    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    /**
     * Method to initialize data in the transport company
     */
    private void initializeData(){
        transportCompany = new TransportCompany("La Carreta");
        Proprietor proprietor = new Proprietor("Alejandro", "alejo@gmail.com", "3161971519", "1092850037");
        Proprietor proprietor2 = new Proprietor("Luz", "alejo@gmail.com", "3161971519", "109285003");
        Proprietor proprietor3 = new Proprietor("Hugo", "alejo@gmail.com", "3161971519", "25022");
        PassengerVehicle passengerVehicle = new PassengerVehicle("VAD908","Mercedes", "Red", 2005, proprietor, 2);
        PassengerVehicle passengerVehicle2 = new PassengerVehicle("VAD905","Toyota", "Red", 2005, proprietor2, 1);
        PassengerVehicle passengerVehicle3 = new PassengerVehicle("VAD906","Mazda", "Red", 2005, proprietor3, 2);
        PassengerVehicle passengerVehicle4 = new PassengerVehicle("VAD907","Mazda", "Blue", 2005, proprietor, 2);
        transportCompany.addUser("Veronica", 21, 65);
        transportCompany.addUser("Mariana", 21, 65);
        transportCompany.addProprietor(proprietor);
        transportCompany.addProprietor(proprietor2);
        transportCompany.addVehicle(passengerVehicle);
        transportCompany.addVehicle(passengerVehicle2);
        transportCompany.addVehicle(passengerVehicle3);
        transportCompany.addUserToVehicle(passengerVehicle, "Veronica");
        transportCompany.addUserToVehicle(passengerVehicle, "Mariana");
        transportCompany.updateUser("Veronica", "Carol", 21, 65, passengerVehicle2);
    }

    /*public boolean addUser(User user){
        return transportCompany.addUser(user);
    }*/

    public boolean deleteUser(String name){
        return transportCompany.deleteUser(name);
    }

    /*
    public boolean updateUser(String name, User newUser){
        return transportCompany.updateUser(name, newUser);
    }*/

    public boolean addProprietor(Proprietor proprietor){
        return transportCompany.addProprietor(proprietor);
    }

    public boolean deleteProprietor(String id) {
        return transportCompany.deleteProprietor(id);
    }

    public boolean updateProprietor(String id, Proprietor newProprietor){
        return transportCompany.updateProprietor(id, newProprietor);
    }

    public boolean addVehicle(Vehicle vehicle){
       return transportCompany.addVehicle(vehicle);
    }

    public boolean deleteVehicle(String plate){
        return transportCompany.deleteVehicle(plate);
    }

    public boolean updateVehicle(String plate, Vehicle newVehicle){
        return transportCompany.updateVehicle(plate, newVehicle);
    }

    /*
    public boolean addUserToVehicle(PassengerVehicle passengerVehicle, User user){
        return transportCompany.addUserToVehicle(passengerVehicle, user);
    }*/

    public boolean deleteUserFromVehicle(User user){
        return transportCompany.deleteUserFromVehicle(user);
    }

    public boolean addProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        return transportCompany.addProprietorAssociated(vehicle, proprietor);
    }

    public boolean deleteProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        return transportCompany.deleteProprietorAssociated(vehicle, proprietor);
    }

    public String numberUsersInPassengerVehicle(String plate){
        return transportCompany.numberUsersInPassengerVehicle(plate);
    }

    public String numberUsersNoMinors(){
        return transportCompany.numberUsersNoMinors();
    }

    public String numberUsersOnDay(){
        return transportCompany.numberUsersOnDay();
    }
}