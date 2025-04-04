package dev.davidlsousa.fintrack_api.controllers;

import dev.davidlsousa.fintrack_api.Model.Ticker;
import dev.davidlsousa.fintrack_api.repositories.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticker")
public class TickerController {

    @Autowired
    TickerRepository repository;

    @GetMapping
    public ResponseEntity<List<Ticker>> getAllTickers() {
        List<Ticker> listTicker = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listTicker);
    }
}
