package com.websystique.springmvc;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.websystique.springmvc.model.User;

public class SpringRestTestClient {

    //public static final String REST_SERVICE_URI = "http://localhost:8080/Spring4MVCCRUDRestService";
    public static final String REST_SERVICE_URI = "http://localhost:8080";

    /* GET */
    @Test
    @SuppressWarnings("unchecked")
    public void listAllUsers() {
        System.out.println("Testing listAllUsers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap =
                restTemplate.getForObject(REST_SERVICE_URI + "/user/", List.class);

        if (usersMap != null) {
            for (LinkedHashMap<String, Object> map : usersMap) {
                System.out.println("User: id=" + map.get("id") + ", Name=" + map.get("name") + ", Age="
                        + map.get("age") + ", Salary=" + map.get("salary"));
            }
        } else {
            System.out.println("No user exist----------");
        }
    }

    /* GET */
    @Test
    public void getUser() {
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
        System.out.println(user);
    }

    /* POST */
    @Test
    public void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(0, "Sarah", 51, 134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/", user, User.class);
        System.out.println("Location: " + uri.toASCIIString());
    }

    /* PUT */
    @Test
    public void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(1, "Tomy", 33, 70000);
        restTemplate.put(REST_SERVICE_URI + "/user/1", user);
        System.out.println(user);
    }

    /* DELETE */
    @Test
    public void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/user/3");
    }


    /* DELETE */
    @Test
    public void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/user/");
    }


}
