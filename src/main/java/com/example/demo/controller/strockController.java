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

    @GetMapping("/Login")
    List<String> LoginColppy() {
        List<String> lista = new ArrayList<String>();
        consumirColppy.Login();
        lista.add(consumirColppy.claveSesion);
        return lista;
    }

    @GetMapping("/ObtenerEmpresas")
    List<String> ObtenerEmpresas() {
        
        List<String> lista = new ArrayList<String>();
        lista.add(consumirColppy.ObtenerEmpresas());
        return lista;
    }


}
