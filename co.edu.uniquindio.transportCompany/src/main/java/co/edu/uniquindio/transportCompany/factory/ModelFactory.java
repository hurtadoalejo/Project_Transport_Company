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
        /*PassengerVehicle passengerVehicle = new PassengerVehicle("VAD908","Mercedes", "Red", 2005, proprietor, 2);
        PassengerVehicle passengerVehicle2 = new PassengerVehicle("VAD905","Toyota", "Red", 2005, proprietor2, 1);
        PassengerVehicle passengerVehicle3 = new PassengerVehicle("VAD906","Mazda", "Red", 2005, proprietor3, 2);
        PassengerVehicle passengerVehicle4 = new PassengerVehicle("VAD907","Mazda", "Blue", 2005, proprietor, 2);*/
        transportCompany.addUser("Veronica", 21, 65);
        transportCompany.addUser("Mariana", 21, 65);
        transportCompany.addProprietor("Alejandro", "alejo@gmail.com", "3161971519", "1092850037");
        transportCompany.addProprietor("Luz", "alejo@gmail.com", "3161971519", "109285003");
        transportCompany.addProprietor("Hugo", "alejo@gmail.com", "3161971519", "25022");
        /*transportCompany.addVehicle(passengerVehicle);
        transportCompany.addVehicle(passengerVehicle2);
        transportCompany.addVehicle(passengerVehicle3);
        transportCompany.addUserToVehicle(passengerVehicle, "Veronica");
        transportCompany.addUserToVehicle(passengerVehicle, "Mariana");*/
        transportCompany.updateUser("Veronica", "Carol", 21, 65, null);
    }

}