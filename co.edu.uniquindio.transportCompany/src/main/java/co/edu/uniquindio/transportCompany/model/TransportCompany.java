package co.edu.uniquindio.transportCompany.model;
import co.edu.uniquindio.transportCompany.builder.CargoVehicleBuilder;
import co.edu.uniquindio.transportCompany.builder.PassengerVehicleBuilder;

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

    /**
     * Method to add one user to the Transport Company's users list
     * @param name Name of the user
     * @param age Age of the user
     * @param weight Weight of the user
     * @return Boolean if the action was done successfully or not
     */
    public boolean addUser(String name, int age, double weight) {
        User userFounded = obtainUser(name);
        if (userFounded == null){
            getUsersList().add(User.builder().name(name).age(age).weight(weight).build());
            return true;
        }
        return false;
    }

    /**
     * Method to obtain one user based on one username given
     * @param username Username given to search
     * @return One user or null depending on whether it could be found
     */
    public User obtainUser(String username) {
        User userFounded = null;
        for (User user : usersList) {
            if (user.getName().equals(username)) {
                userFounded = user;
                break;
            }
        }
        return userFounded;
    }

    /**
     * Method to delete one user to the transport company's users list and classes related to it
     * @param name Name of the user to delete
     * @return Boolean if the action was done successfully or not
     */
    public boolean deleteUser(String name) {
        User userFounded = obtainUser(name);
        if (userFounded != null) {
            getUsersList().remove(userFounded);
            deleteUserPermanentlyFromVehicle(userFounded);
            return true;
        }
        return false;
    }

    /**
     * Method to delete one user that's going to be deleted from anywhere, from its associated vehicle
     * @param user User to be deleted
     * @return Boolean if the action was done successfully or not
     */
    private void deleteUserPermanentlyFromVehicle(User user) {
        if (user != null && user.getVehicleAssociated() != null) {
            user.getVehicleAssociated().getAssociatedUsersList().remove(user);
        }
    }

    /**
     * Method to update one user's information
     * @param oldName Old name of the user
     * @param name New name of the user
     * @param age New age of the user
     * @param weight New weight of the user
     * @param plate Plate of the new vehicle associated of the user
     * @return Boolean if the action was done successfully or not
     */
    public boolean updateUser(String oldName, String name, int age, double weight, String plate) {
        User userFounded = obtainUser(oldName);
        User possibleUser = obtainUser(name);
        if (userFounded != null) {
            if (possibleUser == null || possibleUser.equals(userFounded)) {
                User newUser = User.builder().name(name).age(age)
                        .weight(weight).vehicleAssociated(obtainPassengerVehicle(plate)).build();
                updateUserPassengerVehicle(userFounded, newUser);
                exchangeUserTransportCompany(userFounded, newUser);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to update one user's vehicle associated for a new user's vehicle associated
     * @param oldUser Old user
     * @param newUser New user
     */
    private void updateUserPassengerVehicle(User oldUser, User newUser) {
        if (oldUser.getVehicleAssociated() == null && newUser.getVehicleAssociated() != null) {
            newUser.getVehicleAssociated().getAssociatedUsersList().add(newUser);
        } else if (oldUser.getVehicleAssociated() != null && newUser.getVehicleAssociated() != null) {
            exchangeUserPassengerVehicle(oldUser, newUser);
        } else if (oldUser.getVehicleAssociated() != null && newUser.getVehicleAssociated() == null) {
            oldUser.getVehicleAssociated().getAssociatedUsersList().remove(oldUser);
        }
    }

    /**
     * Method to exchange one user with a new one in the users list of its associated vehicle
     * @param oldUser Old user to be replaced
     * @param newUser New user to replace
     */
    private void exchangeUserPassengerVehicle(User oldUser, User newUser) {
        PassengerVehicle oldPassengerVehicle = oldUser.getVehicleAssociated();
        PassengerVehicle newPassengerVehicle = newUser.getVehicleAssociated();
        if (!oldPassengerVehicle.getPlate().equals(newPassengerVehicle.getPlate()) && newPassengerVehicle.getAssociatedUsersList().size() < newPassengerVehicle.getMaxPassengers()) {
            oldPassengerVehicle.getAssociatedUsersList().remove(oldUser);
            newPassengerVehicle.getAssociatedUsersList().add(newUser);
        }
        else{
            LinkedList<User> associatedUsers = oldUser.getVehicleAssociated().getAssociatedUsersList();
            for (int i = 0; i < associatedUsers.size(); i++) {
                if (associatedUsers.get(i).getName().equals(oldUser.getName())) {
                    associatedUsers.set(i, newUser);
                }
            }
        }
    }

    /**
     * Method to exchange one user for another in the transport company's users list
     * @param oldUser Old user to be replaced
     * @param newUser New user to replace
     */
    private void exchangeUserTransportCompany(User oldUser, User newUser) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getName().equals(oldUser.getName())) {
                usersList.set(i, newUser);
            }
        }
    }

    /**
     * Method to add one proprietor to the transport company's proprietor list
     * @param name Name of the proprietor
     * @param email Email of the proprietor
     * @param phoneNumber Phone number of the proprietor
     * @param id ID of the proprietor
     * @return Boolean if the action was done successfully or not
     */
    public boolean addProprietor(String name, String email, String phoneNumber, String id) {
        Proprietor proprietorFounded = obtainProprietor(id);
        if (proprietorFounded == null) {
            getPropietorsList().add(Proprietor.builder().name(name).email(email)
                    .phoneNumber(phoneNumber).id(id).associatedVehiclesList(new LinkedList<>()).build());
            return true;
        }
        return false;
    }

    /**
     * Method to obtain one proprietor based on one ID given
     * @param id ID given to search
     * @return One user or null depending on whether it could be found
     */
    public Proprietor obtainProprietor(String id) {
        Proprietor proprietorFounded = null;
        for (Proprietor proprietor : getPropietorsList()) {
            if (proprietor.getId().equals(id)) {
                proprietorFounded = proprietor;
                break;
            }
        }
        return proprietorFounded;
    }

    /**
     * Method to delete one proprietor from the transport company's proprietors list and classes related to it
     * @param id ID of the proprietor to delete
     * @return Boolean if the action was done successfully or not
     */
    public boolean deleteProprietor(String id) {
        Proprietor proprietorFounded = obtainProprietor(id);
        if (proprietorFounded != null) {
            deleteProprietorFromVehicles(proprietorFounded);
            deleteVehicleWithProprietor(proprietorFounded.getPrincipalVehicle().getPlate());
            getPropietorsList().remove(proprietorFounded);
            return true;
        }
        return false;
    }

    /**
     * Method to update one proprietor's information
     * @param oldId Old ID of the proprietor
     * @param name New name of the proprietor
     * @param email New email of the proprietor
     * @param phoneNumber New phoneNumber of the proprietor
     * @param id New ID of the proprietor
     * @return Boolean if the action was done successfully or not
     */
    public boolean updateProprietor(String oldId, String name, String email, String phoneNumber, String id) {
        Proprietor proprietorFounded = obtainProprietor(oldId);
        Proprietor possibleProprietor = obtainProprietor(id);
        if (proprietorFounded != null) {
            if (possibleProprietor == null || possibleProprietor.equals(proprietorFounded)) {
                Proprietor newProprietor = Proprietor.builder().name(name).email(email)
                        .phoneNumber(phoneNumber).id(id)
                        .principalVehicle(duplicateVehicle(proprietorFounded.getPrincipalVehicle(),proprietorFounded))
                        .associatedVehiclesList(new LinkedList<>()).build();
                exchangeVehiclesAssociated(proprietorFounded, newProprietor);
                updateVehiclePrincipalProprietor(proprietorFounded.getPrincipalVehicle(), newProprietor);
                exchangeProprietorTransportCompany(proprietorFounded, newProprietor);
                return true;
            }
        }
        return false;
    }

    private Vehicle duplicateVehicle(Vehicle vehicle, Proprietor proprietor) {
        if (vehicle == null) {
            return null;
        }
        if (vehicle instanceof PassengerVehicle passengerVehicle) {
            return new PassengerVehicleBuilder().plate(passengerVehicle.getPlate()).brand(passengerVehicle.getBrand()).
                    colour(passengerVehicle.getColour()).model(passengerVehicle.getModel())
                    .proprietor(proprietor).maxPassengers(passengerVehicle.getMaxPassengers()).
                    associatedUsersList(new LinkedList<>()).associatedProprietorList(new LinkedList<>()).build();
        }
        else if (vehicle instanceof CargoVehicle cargoVehicle) {
            return new CargoVehicleBuilder().plate(cargoVehicle.getPlate()).brand(cargoVehicle.getBrand()).
                    colour(cargoVehicle.getColour()).model(cargoVehicle.getModel()).proprietor(proprietor)
                    .cargoCapacity(cargoVehicle.getCargoCapacity()).axlesNumber(cargoVehicle.getAxlesNumber()).associatedProprietorList(new LinkedList<>()).build();
        }
        return null;
    }

    private void updateProprietorOnlyVehicle(Proprietor proprietor, Vehicle vehicle){
        Proprietor newProprietor = Proprietor.builder().name(proprietor.getName()).email(proprietor.getEmail())
                .phoneNumber(proprietor.getPhoneNumber()).principalVehicle(vehicle)
                .id(proprietor.getId()).associatedVehiclesList(new LinkedList<>()).build();
        exchangeVehiclesAssociated(proprietor, newProprietor);
        exchangeProprietorTransportCompany(proprietor, newProprietor);
    }

    private void exchangeVehiclesAssociated(Proprietor oldProprietor, Proprietor newProprietor) {
        for (Vehicle vehicle : oldProprietor.getAssociatedVehiclesList()) {
            for (int i = 0; i < vehicle.getAssociatedProprietorList().size(); i++) {
                if (vehicle.getAssociatedProprietorList().get(i).getId().equals(oldProprietor.getId())) {
                    vehicle.getAssociatedProprietorList().set(i, newProprietor);
                    newProprietor.getAssociatedVehiclesList().add(vehicle);
                }
            }
        }
    }

    private void updateVehiclePrincipalProprietor(Vehicle vehicle, Proprietor proprietor) {
        if (vehicle != null) {
            if (vehicle instanceof PassengerVehicle passengerVehicle) {
                updatePassengerVehicleOnlyProprietor(passengerVehicle, proprietor);
            } else if (vehicle instanceof CargoVehicle cargoVehicle) {
                updateCargoVehicleOnlyProprietor(cargoVehicle, proprietor);
            }
        }
    }

    private void updatePassengerVehicleOnlyProprietor(PassengerVehicle passengerVehicle, Proprietor proprietor) {
        exchangeUsersPassengerVehicle(passengerVehicle, proprietor.getPrincipalVehicle().getPlate());
        exchangePassengerVehicleTransportCompany(passengerVehicle, (PassengerVehicle) proprietor.getPrincipalVehicle());
    }

    private void updateCargoVehicleOnlyProprietor(CargoVehicle cargoVehicle, Proprietor proprietor) {
        exchangeCargoVehicleTransportCompany(cargoVehicle, (CargoVehicle) proprietor.getPrincipalVehicle());
    }

    /**
     * Method to exchange one proprietor for another in the transport company's proprietors list
     * @param oldProprietor Old proprietor to be replaced
     * @param newProprietor New proprietor to replace
     */
    private void exchangeProprietorTransportCompany(Proprietor oldProprietor, Proprietor newProprietor) {
        for (int i = 0; i < propietorsList.size(); i++) {
            if (propietorsList.get(i).getId().equals(oldProprietor.getId())) {
                propietorsList.set(i, newProprietor);
                return;
            }
        }
    }

    /**
     * Method to add one passenger vehicle to the transport company's passenger vehicles list
     * @param plate Plate of the new passenger vehicle
     * @param brand Brand of the new passenger vehicle
     * @param colour Colour of the new passenger vehicle
     * @param model Model of the new passenger vehicle
     * @param id ID of the passenger vehicle's proprietor
     * @param maxPassengers Max passengers of the new passenger vehicle
     * @return Boolean if the action was done successfully or not
     */
    public boolean addPassengerVehicle(String plate, String brand, String colour, int model,
                                       String id, int maxPassengers) {
        PassengerVehicle passengerVehicle = obtainPassengerVehicle(plate);
        Proprietor proprietor = obtainProprietor(id);
        if (passengerVehicle == null && isProprietorAvailable(proprietor)) {
            PassengerVehicle passengerVehicleCreated = new PassengerVehicleBuilder().plate(plate).brand(brand).
                    colour(colour).model(model).proprietor(proprietor).maxPassengers(maxPassengers).
                    associatedUsersList(new LinkedList<>()).associatedProprietorList(new LinkedList<>()).build();
            getPassengerVehiclesList().add(passengerVehicleCreated);
            updateProprietorOnlyVehicle(proprietor, passengerVehicleCreated);
            return true;
        }
        return false;
    }

    /**
     * Method to add one cargo vehicle to the transport company's cargo vehicles list
     * @param plate Plate of the new cargo vehicle
     * @param brand Brand of the new cargo vehicle
     * @param colour Colour of the new cargo vehicle
     * @param model Model of the new cargo vehicle
     * @param id ID of the cargo vehicle's proprietor
     * @param cargoCapacity Cargo capacity of the new cargo vehicle
     * @param axlesNumber Axles number of the new cargo vehicle
     * @return Boolean if the action was done successfully or not
     */
    public boolean addCargoVehicle(String plate, String brand, String colour, int model,
                                   String id, double cargoCapacity, int axlesNumber) {
        CargoVehicle cargoVehicle = obtainCargoVehicle(plate);
        Proprietor proprietor = obtainProprietor(id);
        if (cargoVehicle == null && isProprietorAvailable(proprietor)) {
            CargoVehicle cargoVehicleCreated = new CargoVehicleBuilder().plate(plate).brand(brand).
                    colour(colour).model(model).proprietor(proprietor).cargoCapacity(cargoCapacity).
                    axlesNumber(axlesNumber).associatedProprietorList(new LinkedList<>()).build();
            getCargoVehiclesList().add(cargoVehicleCreated);
            updateProprietorOnlyVehicle(proprietor, cargoVehicleCreated);
            return true;
        }
        return false;
    }

    /**
     * Method to obtain one cargo vehicle based on the plate given
     * @param plate Plate of the cargo vehicle to search
     * @return One cargo vehicle or null depending on whether it could be found
     */
    public CargoVehicle obtainCargoVehicle(String plate) {
        CargoVehicle cargoVehicle = null;
        for (CargoVehicle tempCargoVehicle : getCargoVehiclesList()) {
            if (tempCargoVehicle.getPlate().equals(plate)) {
                cargoVehicle = tempCargoVehicle;
                break;
            }
        }
        return cargoVehicle;
    }

    /**
     * Method to obtain one passenger vehicle based on the plate given
     * @param plate Plate of the passenger vehicle to search
     * @return One passenger vehicle or null depending on whether it could be found
     */
    public PassengerVehicle obtainPassengerVehicle(String plate) {
        PassengerVehicle passengerVehicle = null;
        for (PassengerVehicle tempPassengerVehicle : getPassengerVehiclesList()) {
            if (tempPassengerVehicle.getPlate().equals(plate)) {
                passengerVehicle = tempPassengerVehicle;
                break;
            }
        }
        return passengerVehicle;
    }

    /**
     * Method to delete a vehicle that is about to be deleted with the proprietor
     * @param plate Plate of the vehicle to delete
     */
    private void deleteVehicleWithProprietor(String plate) {
        Vehicle vehicle = obtainVehicle(plate);
        if (vehicle != null) {
            deleteProprietorsAssociated(vehicle);
            if (vehicle instanceof PassengerVehicle passengerVehicle) {
                deleteUsersFromVehicle(passengerVehicle);
                getPassengerVehiclesList().remove(passengerVehicle);
            }
            else if (vehicle instanceof CargoVehicle cargoVehicle) {
                getCargoVehiclesList().remove(cargoVehicle);
            }
        }
    }

    /**
     * Method to delete one vehicle
     * @param plate Plate of the vehicle to delete
     * @return Boolean if the action was done successfully or not
     */
    public boolean deleteVehicle(String plate) {
        Vehicle vehicle = obtainVehicle(plate);
        if (vehicle != null) {
            deleteProprietorsAssociated(vehicle);
            exchangeProprietorTransportCompanyNoVehicle(vehicle.getProprietor());
            if (vehicle instanceof PassengerVehicle passengerVehicle) {
                deleteUsersFromVehicle(passengerVehicle);
                getPassengerVehiclesList().remove(passengerVehicle);
            }
            else if (vehicle instanceof CargoVehicle cargoVehicle) {
                getCargoVehiclesList().remove(cargoVehicle);
            }
        }
        return false;
    }

    /**
     * Method to update one cargo vehicle's information
     * @param oldPlate Old plate of the cargo vehicle
     * @param plate New plate of the cargo vehicle
     * @param brand New brand of the cargo vehicle
     * @param colour New colour of the cargo vehicle
     * @param model New model of the cargo vehicle
     * @param id New id of the cargo vehicle
     * @param cargoCapacity New cargo capacity of the cargo vehicle
     * @param axlesNumber New axles number of the cargo vehicle
     * @return Boolean if the action was done successfully or not
     */
    public boolean updateCargoVehicle(String oldPlate, String plate, String brand,
                                      String colour, int model, String id, double cargoCapacity, int axlesNumber) {
        CargoVehicle cargoVehicleFounded = obtainCargoVehicle(oldPlate);
        CargoVehicle possibleCargoVehicle = obtainCargoVehicle(plate);
        Proprietor proprietor = obtainProprietor(id);
        if (cargoVehicleFounded != null && proprietor != null) {
            if (possibleCargoVehicle == null || possibleCargoVehicle.getPlate().equals(oldPlate)
                    || !isProprietorInAssociates(cargoVehicleFounded.getAssociatedProprietorList(), proprietor)) {
                CargoVehicle newCargoVehicle = new CargoVehicleBuilder().plate(plate).brand(brand)
                        .colour(colour).model(model).proprietor(proprietor)
                        .associatedProprietorList(cargoVehicleFounded.getAssociatedProprietorList())
                        .cargoCapacity(cargoCapacity).axlesNumber(axlesNumber).build();
                if ((isProprietorAvailable(proprietor)
                        || cargoVehicleFounded.getProprietor().getId().equals(proprietor.getId()))
                        && !isProprietorInAssociates(cargoVehicleFounded.getAssociatedProprietorList(), proprietor)) {
                    exchangeCargoVehicleTransportCompany(cargoVehicleFounded, newCargoVehicle);
                    /*updateProprietor(proprietor.getId(), proprietor.getName(), proprietor.getEmail(), proprietor.getPhoneNumber(), proprietor.getId(), plate);*/
                }
            }
        }
        return false;
    }

    /**
     * Method to exchange one cargoVehicle for another in the transport company's cargo vehicles list
     * @param oldCargoVehicle Old cargo vehicle to be replaced
     * @param newCargoVehicle New cargo vehicle to replace
     */
    private void exchangeCargoVehicleTransportCompany(CargoVehicle oldCargoVehicle, CargoVehicle newCargoVehicle){
        for (int i = 0; i < getCargoVehiclesList().size(); i++) {
            if (getCargoVehiclesList().get(i).getPlate().equals(oldCargoVehicle.getPlate())) {
                getCargoVehiclesList().set(i, newCargoVehicle);
            }
        }
    }

    /**
     * Method to exchange one passengerVehicle for another in the transport company's passenger vehicles list
     * @param oldPassengerVehicle Old passenger vehicle to be replaced
     * @param newPassengerVehicle New passenger vehicle to replace
     */
    private void exchangePassengerVehicleTransportCompany(PassengerVehicle oldPassengerVehicle, PassengerVehicle newPassengerVehicle){
        for (int i = 0; i < getPassengerVehiclesList().size(); i++) {
            if (getPassengerVehiclesList().get(i).getPlate().equals(oldPassengerVehicle.getPlate())) {
                getPassengerVehiclesList().set(i, newPassengerVehicle);
            }
        }
    }

    private void changeAllAssociatedProprietors(Vehicle oldVehicle, Vehicle newVehicle) {
        for (Proprietor proprietor : oldVehicle.getAssociatedProprietorList()){
            for (int i = 0; i < proprietor.getAssociatedVehiclesList().size(); i++) {
                if (proprietor.getAssociatedVehiclesList().get(i).getPlate().equals(oldVehicle.getPlate())) {
                    proprietor.getAssociatedVehiclesList().set(i, newVehicle);
                    newVehicle.getAssociatedProprietorList().add(proprietor);
                }
            }
        }
    }

    /**
     * Method to exchange one proprietor from the transport company's proprietors list for
     * the same proprietor without the principal vehicle
     * @param proprietor Proprietor given to exchange
     */
    private void exchangeProprietorTransportCompanyNoVehicle(Proprietor proprietor) {
        Proprietor newProprietor = Proprietor.builder().name(proprietor.getName()).email(proprietor.getEmail())
                .phoneNumber(proprietor.getPhoneNumber()).id(proprietor.getId()).principalVehicle(null)
                .associatedVehiclesList(proprietor.getAssociatedVehiclesList()).build();
        exchangeProprietorTransportCompany(proprietor, newProprietor);
    }

    /**
     * Method to obtain one vehicle based on the plate given
     * @param plate Plate of the vehicle to search
     * @return One vehicle or null depending on whether it could be found
     */
    public Vehicle obtainVehicle(String plate) {
        Vehicle vehicle = obtainCargoVehicle(plate);
        if (vehicle != null) {
            return vehicle;
        }
        vehicle = obtainPassengerVehicle(plate);
        return vehicle;
    }

    /**
     * Method to verify if a proprietor is one of the associated proprietors
     * @param associatesList Associates list to find
     * @param proprietor Proprietor to verify
     * @return Boolean if the proprietor was found or not
     */
    private boolean isProprietorInAssociates(LinkedList<Proprietor> associatesList, Proprietor proprietor){
        for (Proprietor temporalProprietor : associatesList) {
            if (temporalProprietor.getId().equals(proprietor.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method verify if a proprietor doesn't have a principal vehicle
     * @param proprietor Proprietor to verify
     * @return Boolean if the proprietor is available or not
     */
    private boolean isProprietorAvailable(Proprietor proprietor) {
        boolean isAvailable = false;
        if (proprietor != null) {
            if (proprietor.getPrincipalVehicle() == null) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    /**
     * Method to add one user to the associated users list of one passenger vehicle
     * @param plate Plate of the vehicle to get associated with
     * @param name Name of the user to associate
     * @return Boolean if the action was done successfully or not
     */
    public boolean addUserToVehicle(String plate, String name){
        User userFound = obtainUser(name);
        PassengerVehicle passengerVehicle = obtainPassengerVehicle(plate);
        if (userFound == null || passengerVehicle == null || userFound.getVehicleAssociated() != null ||
                passengerVehicle.getAssociatedUsersList().size() >= passengerVehicle.getMaxPassengers()) {
            return false;
        }
        if (!passengerVehicle.getAssociatedUsersList().contains(userFound)) {
            updateUser(userFound.getName(), userFound.getName(), userFound.getAge(),
                    userFound.getWeight(), plate);
            return true;
        }
        return false;
    }

    /**
     * Method to delete all the users associated from a vehicle
     * @param passengerVehicle Passenger vehicle given
     */
    private void deleteUsersFromVehicle(PassengerVehicle passengerVehicle) {
        if (passengerVehicle != null) {
            for (User user : passengerVehicle.getAssociatedUsersList()) {
                updateUser(user.getName(), user.getName(), user.getAge(), user.getWeight(), null);
            }
            passengerVehicle.getAssociatedUsersList().clear();
        }
    }

    /**
     * Method to exchange all the users of one passenger vehicle to another
     * @param passengerVehicle Passenger vehicle given
     * @param newPlate Plate of the new passenger vehicle to assign
     */
    private void exchangeUsersPassengerVehicle(PassengerVehicle passengerVehicle, String newPlate) {
        PassengerVehicle newPassengerVehicle = obtainPassengerVehicle(newPlate);
        if (passengerVehicle != null) {
            for (User user : passengerVehicle.getAssociatedUsersList()) {
                updateUser(user.getName(), user.getName(), user.getAge(), user.getWeight(), newPlate);
                newPassengerVehicle.getAssociatedUsersList().add(user);
            }
        }
    }

    /**
     * Method to delete one user from its associated vehicle
     * @param name Name of the user to delete association
     * @return Boolean if the action was done successfully or not
     */
    public boolean deleteUserFromVehicle(String name) {
        User user = obtainUser(name);
        if (user != null && user.getVehicleAssociated() != null) {
            updateUser(user.getName(), user.getName(), user.getAge(), user.getWeight(), null);
            user.getVehicleAssociated().getAssociatedUsersList().remove(user);
            return true;
        }
        return false;
    }

    /**
     * Method to add one proprietor to a vehicle's associated proprietors list
     * @param plate Plate of the vehicle to get associated with
     * @param id ID of the proprietor to associate
     * @return Boolean if the action was done successfully or not
     */
    public boolean addProprietorAssociated(String plate, String id){
        Proprietor proprietor = obtainProprietor(id);
        Vehicle vehicle = obtainVehicle(plate);
        if (proprietor == null || vehicle == null) {
            return false;
        }
        if (!isProprietorInAssociates(vehicle.getAssociatedProprietorList(), proprietor) &&
                !proprietor.getId().equals(vehicle.getProprietor().getId())){
            vehicle.getAssociatedProprietorList().add(proprietor);
            proprietor.getAssociatedVehiclesList().add(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Method to delete one proprietor from a vehicle's associated proprietors list
     * @param plate Plate of the vehicle to delete association
     * @param id ID of the proprietor to delete association
     * @return Boolean if the action was done successfully or not
     */
    public boolean deleteProprietorVehicleAssociated(String plate, String id){
        Vehicle vehicle = obtainVehicle(plate);
        Proprietor proprietor = obtainProprietor(id);
        if (proprietor == null || vehicle == null) {
            return false;
        }
        if (vehicle.getAssociatedProprietorList().contains(proprietor)) {
            vehicle.getAssociatedProprietorList().remove(proprietor);
            proprietor.getAssociatedVehiclesList().remove(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Method delete all the proprietor from a vehicle's associated proprietors list
     * @param vehicle Vehicle given
     */
    private void deleteProprietorsAssociated(Vehicle vehicle){
        for (Proprietor proprietor : vehicle.getAssociatedProprietorList()) {
            proprietor.getAssociatedVehiclesList().remove(vehicle);
        }
        vehicle.getAssociatedProprietorList().clear();
    }

    /**
     * Method delete one proprietor from all of its associated vehicles list
     * @param proprietor Proprietor to delete
     */
    private void deleteProprietorFromVehicles(Proprietor proprietor){
        for (Vehicle vehicle : proprietor.getAssociatedVehiclesList()) {
            deleteProprietorVehicleAssociated(vehicle.getPlate(), proprietor.getId());
        }
    }

    public String numberUsersInPassengerVehicle(String plate){
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

    /**
     * Method to count the number of users that are in a passenger vehicle
     * @param plate Plate of the passenger vehicle
     * @return Counter
     */
    public int countUsersInPassengerVehicle(String plate){
        int counter = 0;
        for(PassengerVehicle passengerVehicle : passengerVehiclesList){
            if (passengerVehicle.getPlate().equals(plate)) {
                counter = passengerVehicle.getAssociatedUsersList().size();
            }
        }
        return counter;
    }

    /**
     * Method to give a message about the number of users that are more than 17 years old
     * @return Personalized message
     */
    public String numberUsersNoMinors(){
        String message = "No hay usuarios mayores de edad";
        int counter = countUsersNoMinors();
        if (counter != 0){
            message = "Hay " + counter + " usuarios, que son mayores de edad";
        }
        return message;
    }

    /**
     * Method to count the number of users that are more than 17 years old
     * @return Counter
     */
    public int countUsersNoMinors(){
        int counter = 0;
        for(User user : usersList){
            if (user.getAge() >= 18){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Method to give a message about the number of users transported
     * @return Personalized message
     */
    public String numberUsersOnDay(){
        String message = "No hubo ningun usuario transportado el dia de hoy.";
        int counter = countTransportedUsers();
        if (counter != 0){
            message = "El numero de usuarios el dia de hoy fue de: " + counter;
        }
        return message;
    }

    /**
     * Method to count the number of users that have a passenger vehicle associated
     * @return Counter
     */
    public int countTransportedUsers(){
        int counter = 0;
        for(User user : usersList){
            if (user.getVehicleAssociated() != null) {
                counter++;
            }
        }
        return counter;
    }
}