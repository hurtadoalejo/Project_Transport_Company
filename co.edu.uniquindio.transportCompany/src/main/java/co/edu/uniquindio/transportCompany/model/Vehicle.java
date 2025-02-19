package co.edu.uniquindio.transportCompany.model;

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
     */
    public Vehicle(String plate, String brand, String colour, int model, Proprietor proprietor) {
        this.plate = plate;
        this.brand = brand;
        this.colour = colour;
        this.model = model;
        this.proprietor = proprietor;
        this.associatedProprietorList = new LinkedList<>();
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

    /**
     * Method to modify the vehicle's plate
     * @param plate New vehicle plate
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * Method to modify the vehicle's brand
     * @param brand New vehicle brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Method to modify the vehicle's colour
     * @param colour New vehicle colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Method to modify the vehicle's model
     * @param model New vehicle model
     */
    public void setModel(int model) {
        this.model = model;
    }

    /**
     * Method to modify the vehicle's proprietor
     * @param proprietor New vehicle proprietor
     */
    public void setProprietor(Proprietor proprietor) {
        this.proprietor = proprietor;
    }

    /**
     * Method to obtain the vehicle's associated proprietors list
     * @param associatedProprietorList New associated proprietors list
     */
    public void setAssociatedProprietorList(LinkedList<Proprietor> associatedProprietorList) {
        this.associatedProprietorList = associatedProprietorList;
    }
}