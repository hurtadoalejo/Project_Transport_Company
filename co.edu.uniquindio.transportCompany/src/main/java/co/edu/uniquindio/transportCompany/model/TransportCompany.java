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
    private boolean deleteUserPermanentlyFromVehicle(User user) {
        if (user != null && user.getVehicleAssociated() != null) {
            user.getVehicleAssociated().getAssociatedUsersList().remove(user);
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
            if (possibleUser == null || possibleUser.equals(userFounded)) {
                User newUser = User.builder().name(name).age(age).weight(weight).vehicleAssociated(vehicleAssociated).build();
                if (updateUserPassengerVehicle(userFounded, newUser)){
                    exchangeUserTransportCompany(userFounded, newUser);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to update one user's vehicle associated for a new user's vehicle associated
     * @param oldUser Old user
     * @param newUser New user
     * @return Boolean if the action was done successfully or not
     */
    private boolean updateUserPassengerVehicle(User oldUser, User newUser) {
        if (newUser == null) {
            return false;
        }
        if (oldUser.getVehicleAssociated() == null && newUser.getVehicleAssociated() != null) {
            newUser.getVehicleAssociated().getAssociatedUsersList().add(newUser);
            return true;
        } else if (oldUser.getVehicleAssociated() != null && newUser.getVehicleAssociated() != null) {
            exchangeUserPassengerVehicle(oldUser, newUser);
            return true;
        } else if (oldUser.getVehicleAssociated() != null && newUser.getVehicleAssociated() == null) {
            oldUser.getVehicleAssociated().getAssociatedUsersList().remove(oldUser);
            return true;
        }
        else{
            return true;
        }
    }

    /**
     * Method to exchange one user with a new one in the users list of its associated vehicle
     * @param oldUser Old user to be replaced
     * @param newUser New user to replace
     * @return Boolean if the action was done successfully or not
     */
    private boolean exchangeUserPassengerVehicle(User oldUser, User newUser) {
        PassengerVehicle oldPassengerVehicle = oldUser.getVehicleAssociated();
        PassengerVehicle newPassengerVehicle = newUser.getVehicleAssociated();
        if (!oldPassengerVehicle.getPlate().equals(newPassengerVehicle.getPlate()) && newPassengerVehicle.getAssociatedUsersList().size() < newPassengerVehicle.getMaxPassengers()) {
            oldPassengerVehicle.getAssociatedUsersList().remove(oldUser);
            newPassengerVehicle.getAssociatedUsersList().add(newUser);
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
                    .phoneNumber(phoneNumber).id(id).build());
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
            getPropietorsList().remove(proprietorFounded);
            dissociateProprietorVehicleAssociated(proprietorFounded);
            //deleteVehicle(proprietorFounded.getPrincipalVehicle().getPlate());
            return true;
        }
        return false;
    }

    /**
     * Method to dissociate all the vehicles from the proprietor's vehicles associated list
     * @param proprietor Proprietor to be dissociated
     */
    private void dissociateProprietorVehicleAssociated(Proprietor proprietor) {
        if (!proprietor.getAssociatedVehiclesList().isEmpty()) {
            for (Vehicle vehicle : proprietor.getAssociatedVehiclesList()) {
                vehicle.getAssociatedProprietorList().remove(proprietor);
            }
        }
    }

    /**
     * Method to update one proprietor's information
     * @param oldId Old ID of the proprietor
     * @param name New name of the proprietor
     * @param email New email of the proprietor
     * @param phoneNumber New phoneNumber of the proprietor
     * @param id New ID of the proprietor
     * @param vehicleAssociated New vehicleAssociated of the proprietor
     * @return Boolean if the action was done successfully or not
     */
    public boolean updateProprietor(String oldId, String name, String email, String phoneNumber, String id, Vehicle vehicleAssociated) {
        Proprietor proprietorFounded = obtainProprietor(id);
        Proprietor possibleProprietor = obtainProprietor(id);
        if (proprietorFounded != null) {
            if (possibleProprietor == null || possibleProprietor.equals(proprietorFounded)) {
                Proprietor newProprietor = Proprietor.builder().name(name).email(email)
                        .phoneNumber(phoneNumber).id(id).build();
                if (true){
                    exchangeProprietorTransportCompany(proprietorFounded, newProprietor);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to exchange one proprietor for another in the transport company's proprietors list
     * @param oldProprietor Old proprietor to be replaced
     * @param newProprietor New proprietor to replace
     * @return Boolean if the action was done successfully or not
     */
    private boolean exchangeProprietorTransportCompany(Proprietor oldProprietor, Proprietor newProprietor) {
        for (int i = 0; i < propietorsList.size(); i++) {
            if (propietorsList.get(i).getId().equals(oldProprietor.getId())) {
                propietorsList.set(i, newProprietor);
                return true;
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
        if (userFound == null ||
                passengerVehicle.getAssociatedUsersList().size() >= passengerVehicle.getMaxPassengers()) {
            return false;
        }
        if (!passengerVehicle.getAssociatedUsersList().contains(userFound)) {
            updateUser(userFound.getName(), userFound.getName(), userFound.getAge(),
                    userFound.getWeight(), passengerVehicle);
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