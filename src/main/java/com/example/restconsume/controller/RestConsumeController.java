package com.example.restconsume.controller;

import com.example.restconsume.models.Gatos;
import com.example.restconsume.service.GatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gatos")
public class RestConsumeController {
    private GatosService gatosService;
    @Autowired
    public  RestConsumeController(GatosService gatosService) {
        this.gatosService = gatosService;
    }
    @GetMapping("/get")
    public ResponseEntity<Gatos> getGatos() throws IOException {
        Gatos cat = gatosService.verGatos();
        if (cat != null) {
            return new ResponseEntity<Gatos>(cat, HttpStatus.OK);
        }else
        return new ResponseEntity<>(cat, HttpStatus.BAD_REQUEST);
    }
}
