package com.example.demo.controller;


import java.util.*;

import com.example.demo.colppy.ConsumirColppy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class strockController {


    @Autowired
    ConsumirColppy consumirColppy;

    private String claveSesion="";

    @GetMapping("/listarTodosLosProductos")
    List<String> all() {
        List<String> lista = new ArrayList<String>();
 
        Login();
        lista.add(claveSesion);

        return lista;
    }

    private void Login(){

        claveSesion = consumirColppy.Login();

    }
}
