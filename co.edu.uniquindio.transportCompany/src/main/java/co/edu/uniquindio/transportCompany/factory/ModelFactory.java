package co.edu.uniquindio.transportCompany.factory;

import co.edu.uniquindio.transportCompany.builder.PassengerVehicleBuilder;
import co.edu.uniquindio.transportCompany.model.*;

import java.util.LinkedList;

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
        transportCompany.addUser("Veronica", 21, 65);
        transportCompany.addUser("Mariana", 21, 65);
        transportCompany.addProprietor("Alejandro", "alejo@gmail.com", "3161971519", "1");
        transportCompany.addProprietor("Luz", "alejo@gmail.com", "3161971519", "2");
        transportCompany.addProprietor("Hugo", "alejo@gmail.com", "3161971519", "3");
        transportCompany.addProprietor("Thomas", "alejo@gmail.com", "3161971519", "4");
        transportCompany.addPassengerVehicle("VAD905","Mercedes", "Red", 2005, "1", 2);
        transportCompany.addPassengerVehicle("VAD906","Mercedes", "Red", 2005, "2", 2);
        transportCompany.addCargoVehicle("VAD907","Toyota", "Red", 2005, "3",56, 4);
        transportCompany.addUserToVehicle("VAD905", "Veronica");
        transportCompany.addUserToVehicle("VAD905", "Mariana");
        transportCompany.addProprietorAssociated("VAD907", "1");
        transportCompany.addProprietorAssociated("VAD907", "2");
        transportCompany.updateProprietor("1","Alejandro", "alejo@gmail.com", "3161971519", "20", "VAD905");
        //transportCompany.updateCargoVehicle("VAD907", "VAD908", "Toyota", "Red", 2005, "3",56, 4);
    }

}