package dev.davidlsousa.fintrack_api.controllers;

import dev.davidlsousa.fintrack_api.Model.tickers.Ticker;
import dev.davidlsousa.fintrack_api.Model.tickers.TickerRequestDTO;
import dev.davidlsousa.fintrack_api.repositories.TickerRepository;
import dev.davidlsousa.fintrack_api.services.tickerInfo.TickerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticker")
public class TickerController {

    @Autowired
    TickerRepository repository;
    @Autowired
    TickerInfoService tickerInfoService;

    @GetMapping
    public ResponseEntity<List<Ticker>> getAllTickers() {
        List<Ticker> listTicker = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listTicker);
    }

    @PostMapping
    public ResponseEntity<String> createTicker(@RequestBody TickerRequestDTO tickerRequestDTO) {

//        Ticker ticker = tickerInfoService.getInfoTicker(tickerRequestDTO.getTicker());
//
//        if (ticker == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticker not found");
//        }

        double price = tickerRequestDTO.getTotalValuePurchased() / tickerRequestDTO.getNumberOfTicker();

        Ticker ticker = new Ticker(
                tickerRequestDTO.getTicker(),
                tickerRequestDTO.getTicker(),
                tickerRequestDTO.getNumberOfTicker(),
                tickerRequestDTO.getTotalValuePurchased(),
                price,
                price,
                price
        );

        repository.save(ticker);

        return ResponseEntity.status(HttpStatus.OK).body("Ticker added");
    }
}
