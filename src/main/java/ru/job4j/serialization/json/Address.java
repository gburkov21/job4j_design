package ru.job4j.serialization.json;

public class Address {
    private String city;
    private String street;
    private int houseNumber;
    private int apartmentNumber;

    public Address(String city, String street, int houseNumber, int apartmentNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString() {
        return String.format("Address{city='%s', street='%s', houseNumber=%d, apartmentNumber=%d}", city, street, houseNumber, apartmentNumber);
    }
}
