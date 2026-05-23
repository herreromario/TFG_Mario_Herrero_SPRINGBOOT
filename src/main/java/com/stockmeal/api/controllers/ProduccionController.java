package com.stockmeal.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stockmeal.api.dto.ProduccionDTO;
import com.stockmeal.api.dto.ProduccionRequestDTO;
import com.stockmeal.api.services.ProduccionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produccion")
public class ProduccionController {

    @Autowired
    ProduccionService produccionService;

    @GetMapping
    public List<ProduccionDTO> getHistorico() {
        return produccionService.getHistorico();
    }

    @GetMapping("/fecha/{fecha}")
    public List<ProduccionDTO> getByFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return produccionService.getByFecha(fecha);
    }

    @GetMapping("/rango")
    public List<ProduccionDTO> getByRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return produccionService.getByRango(desde, hasta);
    }

    @GetMapping("/plato/{idProducto}")
    public List<ProduccionDTO> getByPlato(@PathVariable Integer idProducto) {
        return produccionService.getByPlato(idProducto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProduccionDTO registrar(@Valid @RequestBody ProduccionRequestDTO request) {
        return produccionService.registrar(request);
    }
}
