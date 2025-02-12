package co.edu.uniquindio.transportCompany;

public class CargoVehicle extends Vehicle{
    private double cargoCapacity;
    private int axlesNumber;

    /**
     * The constructor method for the class Cargo_Vehicle
     * @param plate Plate of the vehicle to create
     * @param brand Brand of the vehicle to create
     * @param colour Colour of the vehicle to create
     * @param model Model of the vehicle to create
     * @param cargoCapacity Cargo capacity of the vehicle to create
     * @param axlesNumber Axles number of the vehicle to create
     * @param proprietor Associated of the vehicle to create
     */
    public CargoVehicle(String plate, String brand, String colour, int model,
            double cargoCapacity, int axlesNumber, Proprietor proprietor) {
        super(plate, brand, colour, model, proprietor);
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

    /**
     * Method to modify the vehicle's cargo capacity
     * @param cargoCapacity New vehicle cargo capacity
     */
    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    /**
     * Method to modify the vehicle's axles number
     * @param axlesNumber New number of vehicle axles
     */
    public void setAxlesNumber(int axlesNumber) {
        this.axlesNumber = axlesNumber;
    }
}