package ru.job4j.serialization.json;

import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonToPojoMain {
    public static void main(String[] args) throws Exception {
        Address address = new Address("Moscow", "Pushkina", 33, 57);
        String[] hobbies = {"fishing, hunting"};
        JSONObject jsonAddress = new JSONObject(address);
        JSONObject jsonHobbies = new JSONObject(hobbies);

        User user = new User(false, 35, "Ivan", address, hobbies);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isMarried", user.isMarried());
        jsonObject.put("age", user.getAge());
        jsonObject.put("name", user.getName());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("hobbies", jsonHobbies);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(user));

    }
}
