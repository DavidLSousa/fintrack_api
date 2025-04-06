package dev.davidlsousa.fintrack_api.services.tickerInfo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.net.SocketAppender;
import dev.davidlsousa.fintrack_api.Model.tickers.Ticker;
import dev.davidlsousa.fintrack_api.exceptions.TickerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TickerInfoAdapter implements TickerInfoInterface {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Ticker getInfoTicker(String tickerDTO) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<Ticker> yFinanceFuture = CompletableFuture.supplyAsync(() -> fetchYFinance(tickerDTO), executor);
        CompletableFuture<Ticker> brapiFuture = CompletableFuture.supplyAsync(() -> fetchBrapiBrazil(tickerDTO), executor);
        CompletableFuture<Ticker> coingeckoFuture = CompletableFuture.supplyAsync(() -> fetchCoingecko(tickerDTO), executor);

        CompletableFuture<Void> allDone = CompletableFuture.allOf(yFinanceFuture, brapiFuture, coingeckoFuture);

        return allDone.thenApply(v -> {
            Ticker yFinance = yFinanceFuture.join();
            Ticker brapi = brapiFuture.join();
            Ticker coingecko = coingeckoFuture.join();

            // Você define sua lógica de escolha aqui. Exemplo:
            if (yFinance != null) return yFinance;
            if (brapi != null) return brapi;
            if (coingecko != null) return coingecko;

            throw new TickerNotFoundException(tickerDTO);
        }).join();
    }

    private Ticker fetchYFinance(String ticker) {
        // Implementar chamada real à API
        return null;
    }

    private Ticker fetchBrapiBrazil(String ticker) {
        // Implementar chamada real à API
//        ("https://brapi.dev/api/v2/crypto?coin=%s?currency=BRL?token=${MEU_TOKEN}", ticker); // Cripto
        String url = String.format("https://brapi.dev/api/quote/%s?token=${MEU_TOKEN}", ticker); // BRL

        ResponseEntity<Ticker> res = restTemplate.getForEntity(url, Ticker.class);
        return res.getBody();
    }

    private Ticker fetchCoingecko(String ticker) {
        // Implementar chamada real à API
//        String url = String.format("https://api.coingecko.com/api/v3/coins/%s", ticker);
//
//        ResponseEntity<Ticker> res = restTemplate.getForEntity(url, Ticker.class);
//
//        return res.getBody();
        return null;
    }
}
