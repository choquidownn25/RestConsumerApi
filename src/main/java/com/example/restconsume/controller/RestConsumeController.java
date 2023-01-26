package com.example.restconsume.controller;

import com.example.restconsume.models.Gatos;
import com.example.restconsume.models.User;
import com.example.restconsume.service.GatosService;
import com.example.restconsume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




@RestController
@RequestMapping("/gatos")
public class RestConsumeController {
    private GatosService gatosService;
    private UserService userService;
    @Autowired
    public  RestConsumeController(GatosService gatosService,
                                  UserService userService ) {
        this.gatosService = gatosService;
        this.userService = userService;
    }
    @GetMapping("/get")
    public ResponseEntity<Gatos> getGatos() throws IOException {
        Gatos cat = gatosService.verGatos();
        if (cat != null) {
            return new ResponseEntity<Gatos>(cat, HttpStatus.OK);
        }else
        return new ResponseEntity<>(cat, HttpStatus.BAD_REQUEST);
    }
    //@RequestMapping(value = {"/reports"}, method = {RequestMethod.GET, RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @GetMapping("/reports")
    public ResponseEntity<Object> getCloudHealthReports(){
        //logger.info("Inside get cloud health reporting function..!!!!");
        ResponseEntity<String> responseEntity = null;
        try {
            final String uri = "https://chapi.cloudhealthtech.com/olap_reports";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            header.set(HttpHeaders.AUTHORIZATION, "Bearer API-KEY");

            HttpEntity<String> requestEntity = new HttpEntity<String>("body",header);
            HttpURLConnection c = (HttpURLConnection) new URL("https://chapi.cloudhealthtech.com/olap_reports").openConnection();
            c.setRequestProperty("Accept", "application/json");
            c.setRequestProperty("Authorization", "Bearer abc-xyz-example-apikey-e215d82537ba");
            try {
                System.out.println(c.getResponseCode() + ": " + new String(c.getInputStream().readAllBytes()));
            } catch(Exception e) {
                System.out.println(c.getResponseCode() + ": " + new String(c.getErrorStream().readAllBytes()));
            }
            responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

            String response = responseEntity.getBody();

            return new ResponseEntity<Object>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<Object>("Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/user")
    @ResponseBody
    private String getUser() {
        String uri = "https://jsonplaceholder.typicode.com/users";
        RestTemplate restTemplate = new RestTemplate();

        User user = restTemplate.getForObject(uri, User.class);
        System.out.println("User: " + user);

        return "User detail page.";
    }
    @RequestMapping("/users/{id}")
    @ResponseBody
    private ResponseEntity<User> getUsers(@PathVariable Integer id) {

        User user = userService.getUsers(id);
        if(user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else
        return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
    }
}
