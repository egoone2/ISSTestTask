package ru.osokin.farmerregistry.util.exceptions;

public class FarmerNotFoundException extends RuntimeException{

    public FarmerNotFoundException(String message) {
        super(message);
    }
}
