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
     * @param username Username given to verify
     * @return One user or null depending on whether it could be found
     */
    public User obtainUser(String username) {
        User userFounded = null;
        for (User user : usersList) {
            if (user.getName().equals(username))
                userFounded = user;
        }
        return userFounded;
    }

    /**
     * Method to delete one user to the transport company's users list
     * @param name Name of the user to delete
     * @return Boolean if the method did it successfully or not
     */
    public boolean deleteUser(String name) {
        User userFounded = obtainUser(name);
        if (userFounded != null) {
            usersList.remove(userFounded);
            return true;
        }
        return false;
    }

    /**
     * Method to update one user's information
     * @param oldName Old name of the user
     * @param name New name of the user
     * @param age New age of the user
     * @param weight New weight of the user
     * @param vehicleAssociated New vehicle associated of the user
     * @return Boolean if the action was done successfully or not
     */
    public boolean updateUser(String oldName, String name, int age, double weight, PassengerVehicle vehicleAssociated) {
        User userFounded = obtainUser(oldName);
        User possibleUser = obtainUser(name);
        if (userFounded != null) {
            if (possibleUser == null || possibleUser.getName().equals(userFounded.getName())) {
                User newUser = User.builder().name(name).age(age).weight(weight).vehicleAssociated(vehicleAssociated).build();
                exchangeUserTransportCompany(userFounded, newUser);
                updateUserPassengerVehicle(userFounded, newUser.getName());
                return true;
            }
        }
        return false;
    }

    /**
     * Method to update one user's vehicle associated for a new user's vehicle associated
     * @param oldUser Old user
     * @param name Name of the new user
     * @return Boolean if the action was done successfully or not
     */
    public boolean updateUserPassengerVehicle(User oldUser, String name) {
        User newUser = obtainUser(name);
        if (newUser == null) {
            return false;
        }
        if (oldUser.getVehicleAssociated() == null && newUser.getVehicleAssociated() != null) {
            addUserToVehicle(newUser.getVehicleAssociated(), newUser.getName());
            return true;
        } else if (oldUser.getVehicleAssociated() != null && newUser.getVehicleAssociated() != null) {
            exchangeUserPassengerVehicle(oldUser, newUser);
            return true;
        } else if (oldUser.getVehicleAssociated() != null && newUser.getVehicleAssociated() == null) {
            deleteUserFromVehicle(oldUser);
            return true;
        }

        return false;
    }

    /**
     * Method to exchange one user with a new one in the users list of its associated vehicle
     * @param oldUser Old user to be replaced
     * @param newUser New user to replace
     * @return Boolean if the action was done successfully or not
     */
    public boolean exchangeUserPassengerVehicle(User oldUser, User newUser) {
        if (!oldUser.getVehicleAssociated().getPlate().equals(newUser.getVehicleAssociated().getPlate())) {
            oldUser.getVehicleAssociated().getAssociatedUsersList().remove(oldUser);
            addUserToVehicle(newUser.getVehicleAssociated(), newUser.getName());
            return true;
        }
        else{
            LinkedList<User> associatedUsers = oldUser.getVehicleAssociated().getAssociatedUsersList();
            for (int i = 0; i < associatedUsers.size(); i++) {
                if (associatedUsers.get(i).getName().equals(oldUser.getName())) {
                    associatedUsers.set(i, newUser);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to exchange one user for another in the transport company's users list
     * @param oldUser Old user to be replaced
     * @param newUser New user to replace
     * @return Boolean if the action was done successfully or not
     */
    public boolean exchangeUserTransportCompany(User oldUser, User newUser) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getName().equals(oldUser.getName())) {
                usersList.set(i, newUser);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to add one user to the transport company's proprietor list
     * @param proprietor Proprietor to add
     * @return Boolean if the method did it successfully or not
     */
    public boolean addProprietor(Proprietor proprietor) {
        boolean done = false;
        if (!verifyProprietor(proprietor.getId())){
            propietorsList.add(proprietor);
            done = true;
        }
        return done;
    }

    /**
     * Method to verify if exists one proprietor with the same id as one given
     * @param id ID given to verify
     * @return Boolean if the proprietor was found or not
     */
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

    /**
     * Method to delete one proprietor to the transport company's proprietors list
     * @param id ID of the proprietor to delete
     * @return Boolean if the method did it successfully or not
     */
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

    /**
     * Method to update one proprietor's information
     * @param id ID of the proprietor to update
     * @param newProprietor Proprietor with the new information
     * @return Boolean if the method did it successfully or not
     */
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

    /**
     * Method to add one vehicle to the transport company's vehicles list
     * @param vehicle Vehicle to add
     * @return Boolean if the method did it successfully or not
     */
    public boolean addVehicle(Vehicle vehicle){
        boolean done = false;
        Proprietor proprietor = vehicle.getProprietor();
        if (!verifyVehicle(vehicle.getPlate()) && isProprietorAvailable(proprietor)){
            if (vehicle instanceof CargoVehicle cargoVehicle){
                cargoVehiclesList.add(cargoVehicle);
                proprietor.setPrincipalVehicle(cargoVehicle);
                done = true;
            } else if (vehicle instanceof PassengerVehicle passengerVehicle) {
                passengerVehiclesList.add(passengerVehicle);
                proprietor.setPrincipalVehicle(passengerVehicle);
                done = true;
            }
        }
        return done;
    }

    /**
     * Method to verify if exists one vehicle with the same plate as one given
     * @param plate Plate given to verify
     * @return Boolean if the proprietor was found or not
     */
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

    /**
     * Method to delete one vehicle to the transport company's vehicles list
     * @param plate Plate of the vehicle to delete
     * @return Boolean if the method did it successfully or not
     */
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

    /**
     * Method to update one vehicle's information
     * @param plate Plate of the vehicle to update
     * @param newVehicle Vehicle with the new information
     * @return Boolean if the method did it successfully or not
     */
    public boolean updateVehicle(String plate, Vehicle newVehicle){
        boolean done = false;
        if (newVehicle instanceof PassengerVehicle newPassengerVehicle) {
            for (PassengerVehicle temporalPassengerVehicle : passengerVehiclesList) {
                if (temporalPassengerVehicle.getPlate().equals(plate)) {
                    changeAttributesPassengerVehicle(temporalPassengerVehicle, newPassengerVehicle);
                    done = true;
                }
            }
        }
        else if (newVehicle instanceof CargoVehicle newCargoVehicle) {
            for (CargoVehicle temporalCargoVehicle : cargoVehiclesList) {
                if (temporalCargoVehicle.getPlate().equals(plate)) {
                    changeAttributesCargoVehicle(temporalCargoVehicle, newCargoVehicle);
                    done = true;
                }
            }
        }
        return done;
    }

    /**
     * Method to change attributes of one passenger vehicle
     * @param passengerVehicle Passenger vehicle to change attributes
     * @param newPassengerVehicle Passenger vehicle with the new attributes
     * @return Boolean if the method did it successfully or not
     */
    public boolean changeAttributesPassengerVehicle(PassengerVehicle passengerVehicle
            , PassengerVehicle newPassengerVehicle){
        if (newPassengerVehicle.getMaxPassengers() >= passengerVehicle.getAssociatedUsersList().size() && !isProprietorInAssociates(passengerVehicle.getAssociatedProprietorList()
                , newPassengerVehicle.getProprietor())){
            if (!verifyVehicle(newPassengerVehicle.getPlate())
                    || passengerVehicle.getPlate().equals(newPassengerVehicle.getPlate())){
                if (isProprietorAvailable(newPassengerVehicle.getProprietor())
                        || newPassengerVehicle.getProprietor().equals(passengerVehicle.getProprietor())){
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

    /**
     * Method to change attributes of one cargo vehicle
     * @param cargoVehicle Cargo vehicle to change attributes
     * @param newCargoVehicle Cargo vehicle with the new attributes
     * @return Boolean if the method did it successfully or not
     */
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

    /**
     * Method to verify if a proprietor is on one associatesList
     * @param associatesList Associates list to find
     * @param proprietor Proprietor to verify
     * @return Boolean if the proprietor was found or not
     */
    public boolean isProprietorInAssociates(LinkedList<Proprietor> associatesList, Proprietor proprietor){
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
    public boolean isProprietorAvailable(Proprietor proprietor) {
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
     * @param passengerVehicle Passenger vehicle to get associated with
     * @param name Name of the user to associate
     * @return Boolean if the action was done successfully or not
     */
    public boolean addUserToVehicle(PassengerVehicle passengerVehicle, String name){
        User userFound = obtainUser(name);
        if (userFound == null || passengerVehicle.getAssociatedUsersList().size() >= passengerVehicle.getMaxPassengers()) {
            return false;
        }
        if (!passengerVehicle.getAssociatedUsersList().contains(userFound)) {
            passengerVehicle.getAssociatedUsersList().add(userFound);
            return true;
        }
        return false;
    }

    /**
     * Method to delete all the users associated from a vehicle
     * @param vehicle Vehicle given
     */
    public void deleteUsersFromVehicle(Vehicle vehicle) {
        if (vehicle instanceof PassengerVehicle passengerVehicle){
            for (User user : passengerVehicle.getAssociatedUsersList()) {
                updateUser(user.getName(), user.getName(), user.getAge(), user.getWeight(), null);
            }
            passengerVehicle.getAssociatedUsersList().clear();
        }
    }

    /**
     * Method to delete one user from its associated vehicle
     * @param user User given to delete association
     * @return Boolean if the action was done successfully or not
     */
    public boolean deleteUserFromVehicle(User user) {
        if (user.getVehicleAssociated() != null) {
            updateUser(user.getName(), user.getName(), user.getAge(), user.getWeight(), null);
            return true;
        }
        return false;
    }

    /**
     * Method to add one proprietor to a vehicle's associated proprietors list
     * @param vehicle Vehicle given to get associate with
     * @param proprietor Proprietor to associate
     * @return Boolean if the action was done successfully or not
     */
    public boolean addProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        if (!isProprietorInAssociates(vehicle.getAssociatedProprietorList(), proprietor) && proprietor != vehicle.getProprietor()){
            vehicle.getAssociatedProprietorList().add(proprietor);
            proprietor.getAssociatedVehiclesList().add(vehicle);
            return true;
        }
        return false;
    }

    /**
     * Method to delete one proprietor from a vehicle's associated proprietors list
     * @param vehicle Vehicle given to delete association
     * @param proprietor Proprietor given to delete association
     * @return Boolean if the action was done successfully or not
     */
    public boolean deleteProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        if (vehicle.getAssociatedProprietorList().contains(proprietor)){
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
    public void deleteProprietorsAssociated(Vehicle vehicle){
        for (Proprietor proprietor : vehicle.getAssociatedProprietorList()) {
            deleteProprietorAssociated(vehicle, proprietor);
        }
    }

    /**
     * Method delete one proprietor from all of its associated vehicles list
     * @param proprietor Proprietor to delete
     */
    public void deleteProprietorFromVehicles(Proprietor proprietor){
        for (Vehicle vehicle : proprietor.getAssociatedVehiclesList()) {
            deleteProprietorAssociated(vehicle, proprietor);
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