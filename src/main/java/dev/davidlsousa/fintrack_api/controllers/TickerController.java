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

@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<String> createTickers(@RequestBody List<TickerRequestDTO> tickersDTO) {

//        Ticker ticker = tickerInfoService.getInfoTicker(tickerDTO.getTicker());
//
//        if (ticker == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticker not found");
//        }

        for (TickerRequestDTO dto : tickersDTO) {

            Ticker tickerDB = repository.getByTicker(dto.getTicker());
            if (tickerDB != null) {
                updateTicker(dto, tickerDB);
                continue;
            }

            double price = dto.getTotalValuePurchased() / dto.getNumberOfTicker();

            Ticker ticker = new Ticker(
                    dto.getTicker(),
                    dto.getTicker(),
                    dto.getNumberOfTicker(),
                    dto.getTotalValuePurchased(),
                    price,
                    price,
                    price
            );

            repository.save(ticker);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Ticker added");
    }
    private void updateTicker(TickerRequestDTO tickerDTO, Ticker ticker) {

        int newNumberOfTickers = ticker.getNumberOfTickers() + tickerDTO.getNumberOfTicker();
        double newTotalValuePurchased = ticker.getTotalValuePurchased() + tickerDTO.getTotalValuePurchased();
        double newAveragePrice = (ticker.getTotalValuePurchased() + tickerDTO.getTotalValuePurchased()) / (ticker.getNumberOfTickers() + tickerDTO.getNumberOfTicker());

        ticker.setNumberOfTickers(newNumberOfTickers);
        ticker.setTotalValuePurchased(newTotalValuePurchased);
        ticker.setAveragePrice(newAveragePrice);

        double newPrice = tickerDTO.getTotalValuePurchased() / tickerDTO.getNumberOfTicker();

        if (ticker.getHighestPrice() < newPrice) {
            ticker.setHighestPrice(newPrice);
        } else if (ticker.getLowestPrice() > newPrice) {
            ticker.setLowestPrice(newPrice);
        }

        repository.save(ticker);
    }

    @PutMapping
    public ResponseEntity<Ticker> sellTicker(@RequestBody TickerRequestDTO tickerDTO) {

        Ticker ticker = repository.getByTicker(tickerDTO.getTicker());

        int newNumberOfTickers = ticker.getNumberOfTickers() - tickerDTO.getNumberOfTicker();
        if (newNumberOfTickers == 0) {
            repository.delete(ticker);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        double newTotalValuePurchased = ticker.getTotalValuePurchased() - tickerDTO.getTotalValuePurchased();

        ticker.setNumberOfTickers(newNumberOfTickers);
        ticker.setTotalValuePurchased(newTotalValuePurchased);

        repository.save(ticker);

        return ResponseEntity.status(HttpStatus.OK).body(ticker);
    }
}
