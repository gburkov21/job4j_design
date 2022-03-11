package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private boolean isMarried;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String name;
    @XmlElement
    private Address address;
    @XmlElementWrapper
    @XmlElement(name = "hobby")
    private String[] hobbies;

    public User() {
    }

    public User(boolean isMarried, int age, String name, Address address, String[] hobbies) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
        this.isMarried = isMarried;
    }

    @Override
    public String toString() {
        return String.format("User{isMarried=%s, age=%d, name='%s', address=%s, hobbies=%s}", isMarried, age, name, address, Arrays.toString(hobbies));
    }
}
