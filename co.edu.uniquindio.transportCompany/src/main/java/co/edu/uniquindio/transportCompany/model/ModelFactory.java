package co.edu.uniquindio.transportCompany.model;

public class ModelFactory {
    private static ModelFactory instance;
    private TransportCompany transportCompany;

    private ModelFactory() {
        transportCompany = initializeData();
    }

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    public TransportCompany initializeData() {

        return transportCompany;
    }


    public TransportCompany getTransportCompany() {
        return transportCompany;
    }
}
