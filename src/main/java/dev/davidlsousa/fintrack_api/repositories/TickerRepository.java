package dev.davidlsousa.fintrack_api.repositories;

import dev.davidlsousa.fintrack_api.Model.tickers.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, String> {

    Ticker getByTicker(String ticker);
}
