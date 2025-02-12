package co.edu.uniquindio.transportCompany;

public class Main {
    public static void main(String[] args) {
        TransportCompany transportCompany = new TransportCompany("La Carreta");
        Proprietor proprietor = new Proprietor("Alejandro", "alejo@gmail.com", "3161971519", "1092850037");
        Proprietor proprietor2 = new Proprietor("Luz", "alejo@gmail.com", "3161971519", "109285003");
        Proprietor proprietor3 = new Proprietor("Hugo", "alejo@gmail.com", "3161971519", "25022");
        PassengerVehicle passengerVehicle = new PassengerVehicle("VAD908","Mercedes", "Red", 2005, proprietor, 2);
        PassengerVehicle passengerVehicle2 = new PassengerVehicle("VAD905","Toyota", "Red", 2005, proprietor2, 1);
        PassengerVehicle passengerVehicle3 = new PassengerVehicle("VAD906","Mazda", "Red", 2005, proprietor3, 2);
        PassengerVehicle passengerVehicle4 = new PassengerVehicle("VAD907","Mazda", "Blue", 2005, proprietor, 2);
        passengerVehicle4.getAssociatedProprietorList().add(proprietor3);
        //proprietor2.getAssociatedVehiclesList().add(passengerVehicle4);
        proprietor3.getAssociatedVehiclesList().add(passengerVehicle4);
        User user = new User("Veronica", 21, 65);
        User user2 = new User("Mariana", 21, 65);
        transportCompany.addUser(user);
        transportCompany.addUser(user2);
        transportCompany.addProprietor(proprietor);
        transportCompany.addProprietor(proprietor2);
        transportCompany.addVehicle(passengerVehicle);
        transportCompany.addVehicle(passengerVehicle2);
        transportCompany.addVehicle(passengerVehicle3);
        transportCompany.addUserToVehicle(passengerVehicle, user);
        transportCompany.addUserToVehicle(passengerVehicle, user2);
        transportCompany.addProprietorAssociated(passengerVehicle2, proprietor3);
        transportCompany.deleteUser("Veronica");
        System.out.println(user.getVehicleAssociated());
    }
}