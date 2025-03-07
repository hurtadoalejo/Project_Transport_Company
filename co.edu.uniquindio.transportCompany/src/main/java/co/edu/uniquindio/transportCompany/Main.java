package co.edu.uniquindio.transportCompany;

import co.edu.uniquindio.transportCompany.factory.ModelFactory;
import co.edu.uniquindio.transportCompany.model.PassengerVehicle;
import co.edu.uniquindio.transportCompany.model.Proprietor;
import co.edu.uniquindio.transportCompany.model.TransportCompany;
import co.edu.uniquindio.transportCompany.model.User;

import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        ModelFactory modelFactory = ModelFactory.getInstance();
        TransportCompany transportCompany = modelFactory.getTransportCompany();
        for (User user : transportCompany.getUsersList()){
            System.out.println(user.getName());
        }
        System.out.println();
        System.out.println("Nombres en vehiculos: ");
        for (PassengerVehicle passengerVehicle : transportCompany.getPassengerVehiclesList()){
            System.out.println();
            System.out.println(passengerVehicle.getPlate() + ":");
            for (User user : passengerVehicle.getAssociatedUsersList()){
                System.out.println(user.getName());
            }
        }
    }
}