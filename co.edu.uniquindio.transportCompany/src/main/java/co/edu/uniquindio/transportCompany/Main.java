package co.edu.uniquindio.transportCompany;

import co.edu.uniquindio.transportCompany.factory.ModelFactory;
import co.edu.uniquindio.transportCompany.model.*;

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
            System.out.println(passengerVehicle.getPlate() + " " +passengerVehicle.getAssociatedProprietorList().size() + passengerVehicle.getProprietor().getId() + ":");
            for (User user : passengerVehicle.getAssociatedUsersList()){
                System.out.println(user.getName());
            }
        }
        for (CargoVehicle cargoVehicle : transportCompany.getCargoVehiclesList()){
            System.out.println();
            System.out.println(cargoVehicle.getPlate() + " " + cargoVehicle.getAssociatedProprietorList().size() + cargoVehicle.getProprietor().getId() + ": ");
            System.out.println(cargoVehicle.getProprietor().getName());
        }
        System.out.println();
        System.out.println("Proprietarios: ");
        for (Proprietor proprietor : transportCompany.getPropietorsList()){
            System.out.print(proprietor.getName() + " ");
            System.out.print(proprietor.getPrincipalVehicle() == null);
            System.out.println(" " + proprietor.getAssociatedVehiclesList().size());
        }
    }
}