package co.edu.uniquindio.transportCompany.model;
import java.util.LinkedList;

public class TransportCompany {
    private String name;
    private LinkedList<Proprietor> propietorsList;
    private LinkedList<CargoVehicle> cargoVehiclesList;
    private LinkedList<PassengerVehicle> passengerVehiclesList;
    private LinkedList<User> usersList;

    /**
     * The constructor method for the class TransportCompany
     * @param name Name of the transport company to create
     */
    public TransportCompany(String name) {
        this.name = name;
        this.propietorsList = new LinkedList<>();
        this.cargoVehiclesList = new LinkedList<>();
        this.passengerVehiclesList = new LinkedList<>();
        this.usersList = new LinkedList<>();
    }

    /**
     * Method to obtain the transport company's name
     * @return Transport company's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to obtain the transport company's proprietors list
     * @return Transport company's proprietors list
     */
    public LinkedList<Proprietor> getPropietorsList() {
        return propietorsList;
    }

    /**
     * Method to obtain the transport company's cargo vehicles list
     * @return Transport company's cargo vehicles list
     */
    public LinkedList<CargoVehicle> getCargoVehiclesList() {
        return cargoVehiclesList;
    }

    /**
     * Method to obtain the transport company's passenger vehicles list
     * @return Transport company's passenger vehicles list
     */
    public LinkedList<PassengerVehicle> getPassengerVehiclesList() {
        return passengerVehiclesList;
    }

    /**
     * Method to obtain the transport company's users list
     * @return Transport company's users list
     */
    public LinkedList<User> getUsersList() {
        return usersList;
    }

    /**
     * Method to modify the transport company's name
     * @param name New transport company name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to obtain the transport company's proprietors list
     * @param propietorsList New proprietors list of the transport company
     */
    public void setPropietorsList(LinkedList<Proprietor> propietorsList) {
        this.propietorsList = propietorsList;
    }

    /**
     * Method to modify the transport company's cargo vehicles list
     * @param cargoVehiclesList New cargo vehicles list of the transport company
     */
    public void setCargoVehiclesList(LinkedList<CargoVehicle> cargoVehiclesList) {
        this.cargoVehiclesList = cargoVehiclesList;
    }

    /**
     * Method to modify the transport company's passenger vehicles list
     * @param passengerVehiclesList New passenger vehicles list of the transport company
     */
    public void setPassengerVehiclesList(LinkedList<PassengerVehicle> passengerVehiclesList) {
        this.passengerVehiclesList = passengerVehiclesList;
    }

    /**
     * Method to modify the transport company's users list
     * @param usersList New users list of the transport company
     */
    public void setUsersList(LinkedList<User> usersList) {
        this.usersList = usersList;
    }

    public boolean addUser(User user) {
        boolean done = false;
        if (!verifyUser(user.getName())){
            usersList.add(user);
            done = true;
        }
        return done;
    }

    public boolean verifyUser(String name){
        boolean repeated = false;
        for (User user : usersList) {
            if (user.getName().equals(name)) {
                repeated = true;
                break;
            }
        }
        return repeated;
    }

    public boolean deleteUser(String name) {
        boolean done = false;
        for (User user : usersList) {
            if (user.getName().equals(name)) {
                done = true;
                deleteUserFromVehicle(user);
                usersList.remove(user);
                break;
            }
        }
        return done;
    }

    public boolean updateUser(String name, User newUser){
        boolean done = false;
        for (User user : usersList) {
            if (user.getName().equals(name)) {
                if (!verifyUser(newUser.getName()) || newUser.getName().equals(name)) {
                    user.setName(newUser.getName());
                    user.setAge(newUser.getAge());
                    user.setWight(newUser.getWight());
                    done = true;
                    break;
                }
            }
        }
        return done;
    }

    public boolean addProprietor(Proprietor proprietor) {
        boolean done = false;
        if (!verifyProprietor(proprietor.getId())){
            propietorsList.add(proprietor);
            done = true;
        }
        return done;
    }

    public boolean verifyProprietor(String id){
        boolean repeated = false;
        for (Proprietor proprietor : propietorsList) {
            if (proprietor.getId().equals(id)) {
                repeated = true;
                break;
            }
        }
        return repeated;
    }

    public boolean deleteProprietor(String id) {
        boolean done = false;
        for (Proprietor proprietor : propietorsList) {
            if (proprietor.getId().equals(id)) {
                deleteProprietorFromVehicles(proprietor);
                if (proprietor.getPrincipalVehicle() != null) {
                    deleteUsersFromVehicle(proprietor.getPrincipalVehicle());
                    deleteVehicle(proprietor.getPrincipalVehicle().getPlate());
                }
                propietorsList.remove(proprietor);
                done = true;
                break;
            }
        }
        return done;
    }

    public boolean updateProprietor(String id, Proprietor newProprietor){
        boolean done = false;
        for (Proprietor proprietor : propietorsList) {
            if (proprietor.getId().equals(id)) {
                if (!verifyProprietor(newProprietor.getId()) || newProprietor.getId().equals(id)) {
                    if (isProprietorAvailable(newProprietor) || newProprietor.getPrincipalVehicle().equals(proprietor.getPrincipalVehicle())) {
                        proprietor.setId(newProprietor.getId());
                        proprietor.setName(newProprietor.getName());
                        proprietor.setEmail(newProprietor.getEmail());
                        proprietor.setPhoneNumber(newProprietor.getPhoneNumber());
                        done = true;
                        break;
                    }
                }
            }
        }
        return done;
    }

    public boolean addVehicle(Vehicle vehicle){
        boolean done = false;
        Proprietor proprietor = vehicle.getProprietor();
        if (!verifyVehicle(vehicle.getPlate()) && isProprietorAvailable(proprietor)){
            if (vehicle instanceof CargoVehicle){
                CargoVehicle cargoVehicle = (CargoVehicle) vehicle;
                cargoVehiclesList.add(cargoVehicle);
                proprietor.setPrincipalVehicle(cargoVehicle);
                done = true;
            } else if (vehicle instanceof PassengerVehicle) {
                PassengerVehicle passengerVehicle = (PassengerVehicle) vehicle;
                passengerVehiclesList.add(passengerVehicle);
                proprietor.setPrincipalVehicle(passengerVehicle);
                done = true;
            }
        }
        return done;
    }

    public boolean verifyVehicle(String plate){
        for (Vehicle cargoVehicle : cargoVehiclesList) {
            if (cargoVehicle.getPlate().equals(plate)) {
                return true;
            }
        }
        for (PassengerVehicle passengerVehicle : passengerVehiclesList) {
            if (passengerVehicle.getPlate().equals(plate)) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteVehicle(String plate){
        boolean done = false;
        for (Vehicle cargoVehicle : cargoVehiclesList) {
            if (cargoVehicle.getPlate().equals(plate)) {
                deleteProprietorsAssociated(cargoVehicle);
                cargoVehicle.getProprietor().setPrincipalVehicle(null);
                cargoVehiclesList.remove(cargoVehicle);
                done = true;
                break;
            }
        }
        if (!done) {
            for (PassengerVehicle passengerVehicle : passengerVehiclesList) {
                if (passengerVehicle.getPlate().equals(plate)) {
                    deleteProprietorsAssociated(passengerVehicle);
                    deleteUsersFromVehicle(passengerVehicle);
                    passengerVehicle.getProprietor().setPrincipalVehicle(null);
                    passengerVehiclesList.remove(passengerVehicle);
                    done = true;
                    break;
                }
            }
        }
        return done;
    }

    public boolean updateVehicle(String plate, Vehicle newVehicle){
        boolean done = false;
        if (newVehicle instanceof PassengerVehicle) {
            PassengerVehicle newPassengerVehicle = (PassengerVehicle) newVehicle;
            for (PassengerVehicle temporalPassengerVehicle : passengerVehiclesList) {
                if (temporalPassengerVehicle.getPlate().equals(plate)) {
                    changeAttributesPassengerVehicle(temporalPassengerVehicle, newPassengerVehicle);
                    done = true;
                }
            }
        }
        else if (newVehicle instanceof CargoVehicle) {
            CargoVehicle newCargoVehicle = (CargoVehicle) newVehicle;
            for (CargoVehicle temporalCargoVehicle : cargoVehiclesList) {
                if (temporalCargoVehicle.getPlate().equals(plate)) {
                    changeAttributesCargoVehicle(temporalCargoVehicle, newCargoVehicle);
                    done = true;
                }
            }
        }
        return done;
    }

    public boolean changeAttributesPassengerVehicle(PassengerVehicle passengerVehicle, PassengerVehicle newPassengerVehicle){
        if (newPassengerVehicle.getMaxPassengers() >= passengerVehicle.getAssociatedUsersList().size() && !isProprietorInAssociates(passengerVehicle.getAssociatedProprietorList(), newPassengerVehicle.getProprietor())){
            if (!verifyVehicle(newPassengerVehicle.getPlate()) || passengerVehicle.getPlate().equals(newPassengerVehicle.getPlate())){
                if (isProprietorAvailable(newPassengerVehicle.getProprietor()) || newPassengerVehicle.getProprietor().equals(passengerVehicle.getProprietor())){
                    passengerVehicle.setMaxPassengers(newPassengerVehicle.getMaxPassengers());
                    passengerVehicle.setBrand(newPassengerVehicle.getBrand());
                    passengerVehicle.setModel(newPassengerVehicle.getModel());
                    passengerVehicle.setColour(newPassengerVehicle.getColour());
                    passengerVehicle.setProprietor(newPassengerVehicle.getProprietor());
                    passengerVehicle.setPlate(newPassengerVehicle.getPlate());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean changeAttributesCargoVehicle(CargoVehicle cargoVehicle, CargoVehicle newCargoVehicle){
        if (!isProprietorInAssociates(cargoVehicle.getAssociatedProprietorList(), newCargoVehicle.getProprietor())){
            if (isProprietorAvailable(newCargoVehicle.getProprietor()) || newCargoVehicle.getProprietor().equals(cargoVehicle.getProprietor())){
                if (!verifyVehicle(newCargoVehicle.getPlate()) || newCargoVehicle.getPlate().equals(cargoVehicle.getPlate())){
                    cargoVehicle.setPlate(newCargoVehicle.getPlate());
                    cargoVehicle.setBrand(newCargoVehicle.getBrand());
                    cargoVehicle.setColour(newCargoVehicle.getColour());
                    cargoVehicle.setModel(newCargoVehicle.getModel());
                    cargoVehicle.setCargoCapacity(newCargoVehicle.getCargoCapacity());
                    cargoVehicle.setAxlesNumber(newCargoVehicle.getAxlesNumber());
                    cargoVehicle.setProprietor(newCargoVehicle.getProprietor());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isProprietorInAssociates(LinkedList<Proprietor> associatesList, Proprietor proprietor){
        for (Proprietor temporalProprietor : associatesList) {
            if (temporalProprietor.getId().equals(proprietor.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean isProprietorAvailable(Proprietor proprietor) {
        boolean isAvailable = false;
        if (proprietor != null) {
            if (proprietor.getPrincipalVehicle() == null) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    public boolean addUserToVehicle(PassengerVehicle passengerVehicle, User user){
        if (!passengerVehicle.getAssociatedUsersList().contains(user) && user.getVehicleAssociated() == null) {
            user.setVehicleAssociated(passengerVehicle);
            passengerVehicle.getAssociatedUsersList().add(user);
            return true;
        }
        return false;
    }

    public void deleteUsersFromVehicle(Vehicle vehicle) {
        if (vehicle instanceof PassengerVehicle){
            PassengerVehicle passengerVehicle = (PassengerVehicle) vehicle;
            for (User user : passengerVehicle.getAssociatedUsersList()) {
                user.setVehicleAssociated(null);
            }
            passengerVehicle.getAssociatedUsersList().clear();
        }
    }

    public boolean deleteUserFromVehicle(User user) {
        if (user.getVehicleAssociated() != null) {
            user.getVehicleAssociated().getAssociatedUsersList().remove(user);
            user.setVehicleAssociated(null);
            return true;
        }
        return false;
    }

    public boolean addProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        if (!isProprietorInAssociates(vehicle.getAssociatedProprietorList(), proprietor) && proprietor != vehicle.getProprietor()){
            vehicle.getAssociatedProprietorList().add(proprietor);
            proprietor.getAssociatedVehiclesList().add(vehicle);
            return true;
        }
        return false;
    }

    public boolean deleteProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        if (vehicle.getAssociatedProprietorList().contains(proprietor)){
            vehicle.getAssociatedProprietorList().remove(proprietor);
            proprietor.getAssociatedVehiclesList().remove(vehicle);
            return true;
        }
        return false;
    }

    public void deleteProprietorsAssociated(Vehicle vehicle){
        for (Proprietor proprietor : vehicle.getAssociatedProprietorList()) {
            deleteProprietorAssociated(vehicle, proprietor);
        }
    }

    public void deleteProprietorFromVehicles(Proprietor proprietor){
        for (Vehicle vehicle : proprietor.getAssociatedVehiclesList()) {
            deleteProprietorAssociated(vehicle, proprietor);
        }
    }

    public String searchUsersInPassengerVehicle(String plate){
        String message = "";
        int counter = countUsersInPassengerVehicle(plate);
        if (counter == 0) {
            message = "El vehiculo de placas " + plate + ", no tiene usuarios asociados.";
        }
        else if (counter == 1) {
            message = "El vehiculo de placas " + plate + ", tiene " + counter + " usuario asociado.";
        }
        else if (counter > 1){
            message = "El vehiculo de placas " + plate + ", tiene " + counter + " usuarios asociados.";
        }
        return message;
    }

    public int countUsersInPassengerVehicle(String plate){
        int counter = 0;
        for(PassengerVehicle passengerVehicle : passengerVehiclesList){
            if (passengerVehicle.getPlate().equals(plate)) {
                counter = passengerVehicle.getAssociatedUsersList().size();
            }
        }
        return counter;
    }
}