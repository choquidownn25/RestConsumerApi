package com.example.restconsume.service;

import com.example.restconsume.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    public User getUsers( Integer id) {
        String uri = "https://jsonplaceholder.typicode.com/users/"+id;
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(uri, User.class);
        return user;
    }
}
