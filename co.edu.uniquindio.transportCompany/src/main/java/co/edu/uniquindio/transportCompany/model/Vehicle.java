package co.edu.uniquindio.transportCompany.model;

import co.edu.uniquindio.transportCompany.builder.VehicleBuilder;

import java.util.LinkedList;

public class Vehicle {
    private String plate, brand, colour;
    private int model;
    private Proprietor proprietor;
    private LinkedList<Proprietor> associatedProprietorList;

    /**
     * The constructor method for the class Vehicle
     * @param plate Plate of the vehicle to create
     * @param brand Brand of the vehicle to create
     * @param colour Colour of the vehicle to create
     * @param model Model of the vehicle to create
     * @param proprietor Proprietor of the vehicle to create
     * @param associatedProprietorList Associated proprietor list of the vehicle to create
     */
    public Vehicle(String plate, String brand, String colour, int model, Proprietor proprietor, LinkedList<Proprietor> associatedProprietorList) {
        this.plate = plate;
        this.brand = brand;
        this.colour = colour;
        this.model = model;
        this.proprietor = proprietor;
        this.associatedProprietorList = associatedProprietorList;
    }

    /**
     * Method to obtain the vehicle's plate
     * @return Vehicle's plate
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Method to obtain the vehicle's brand
     * @return Vehicle's brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Method to obtain the vehicle's colour
     * @return Vehicle's colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Method to obtain the vehicle's model
     * @return Vehicle's model
     */
    public int getModel() {
        return model;
    }

    /**
     * Method to obtain the vehicle's proprietor
     * @return Vehicle's proprietor
     */
    public Proprietor getProprietor() {
        return proprietor;
    }

    /**
     * Method to obtain the vehicle's associated proprietors list
     * @return Vehicle's associated proprietors list
     */
    public LinkedList<Proprietor> getAssociatedProprietorList() {
        return associatedProprietorList;
    }
}