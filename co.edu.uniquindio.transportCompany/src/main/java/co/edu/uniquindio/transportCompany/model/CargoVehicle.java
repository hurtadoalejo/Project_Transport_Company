package co.edu.uniquindio.transportCompany.model;

import java.util.LinkedList;

public class CargoVehicle extends Vehicle{
    private double cargoCapacity;
    private int axlesNumber;

    /**
     * The constructor method for the class CargoVehicle
     * @param plate Plate of the CargoVehicle to create
     * @param brand Brand of the CargoVehicle to create
     * @param colour Colour of the CargoVehicle to create
     * @param model Model of the CargoVehicle to create
     * @param proprietor Proprietor of the CargoVehicle to create
     * @param associatedProprietorList Associated proprietor list of the CargoVehicle to create
     * @param cargoCapacity Cargo capacity of the CargoVehicle to create
     * @param axlesNumber Axles number of the CargoVehicle to create
     */
    public CargoVehicle(String plate, String brand, String colour, int model,
                        Proprietor proprietor, LinkedList<Proprietor> associatedProprietorList,
                        double cargoCapacity, int axlesNumber) {
        super(plate, brand, colour, model, proprietor, associatedProprietorList);
        this.cargoCapacity = cargoCapacity;
        this.axlesNumber = axlesNumber;
    }

    /**
     * Method to obtain the vehicle's cargo capacity
     * @return Vehicle's cargo capacity
     */
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    /**
     * Method to obtain the vehicle's axles number
     * @return Vehicle's axles number
     */
    public int getAxlesNumber() {
        return axlesNumber;
    }
}