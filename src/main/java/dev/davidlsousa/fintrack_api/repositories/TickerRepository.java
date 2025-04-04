package dev.davidlsousa.fintrack_api.repositories;

import dev.davidlsousa.fintrack_api.Model.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, UUID> {
}
