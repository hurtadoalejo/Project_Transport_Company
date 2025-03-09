package co.edu.uniquindio.transportCompany.builder;

import co.edu.uniquindio.transportCompany.model.CargoVehicle;
import co.edu.uniquindio.transportCompany.model.Vehicle;

public class CargoVehicleBuilder extends VehicleBuilder<CargoVehicleBuilder>{
    private double cargoCapacity;
    private int axlesNumber;

    /**
     * Method to modify the vehicle's cargo capacity
     * @param cargoCapacity New cargo capacity of the cargo Vehicle
     * @return The current CargoVehicleBuilder instance
     */
    public CargoVehicleBuilder cargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
        return self();
    }

    /**
     * Method to modify the vehicle's axles number
     * @param axlesNumber New number of the cargo Vehicle axles
     * @return The current CargoVehicleBuilder instance
     */
    public CargoVehicleBuilder axlesNumber(int axlesNumber) {
        this.axlesNumber = axlesNumber;
        return self();
    }

    /**
     * Method to return an object called T
     * @return The current CargoVehicleBuilder instance
     */
    @Override
    protected CargoVehicleBuilder self() {
        return this;
    }

    /**
     * Method to build one CargoVehicle instance based on the current attributes
     * @return New CargoVehicle
     */
    @Override
    public CargoVehicle build() {
        return new CargoVehicle(plate, brand, colour, model, proprietor,
                associatedProprietorList, cargoCapacity, axlesNumber);
    }
}
