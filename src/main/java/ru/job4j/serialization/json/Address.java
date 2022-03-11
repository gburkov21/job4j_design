package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {
    @XmlAttribute
    private String city;
    @XmlAttribute
    private String street;
    @XmlAttribute
    private int houseNumber;
    @XmlAttribute
    private int apartmentNumber;

    public Address() {
    }

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
