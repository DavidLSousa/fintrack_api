package dev.davidlsousa.fintrack_api.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity(name = "tickers")
@Table(name = "tickers")
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nameTicket;

    @Column(nullable = false, unique = true)
    private String ticket;

    @Column(nullable = false)
    private int numberOfTickets;

    @Column(nullable = false)
    private double totalValuePurchased;

    @Column(nullable = false)
    private double highestPrice;

    @Column(nullable = false)
    private double lowestPrice;

    @Column(nullable = false)
    private double averagePrice;

//    @ElementCollection
//    @CollectionTable(name = "ticket_history", joinColumns = @JoinColumn(name = "ticket_id"))
//    private List<Map<String, Object>> history;

    // Validações nos setters
    public void setNumberOfTickets(int numberOfTickets) {
        if (numberOfTickets <= 0) {
            throw new IllegalArgumentException("O número de tickets deve ser maior que 0");
        }
        this.numberOfTickets = numberOfTickets;
    }

    public void setTotalValuePurchased(double totalValuePurchased) {
        if (totalValuePurchased <= 0) {
            throw new IllegalArgumentException("O valor total de compra deve ser maior que 0");
        }
        this.totalValuePurchased = totalValuePurchased;
    }

    public void setHighestPrice(double highestPrice) {
        if (highestPrice <= 0) {
            throw new IllegalArgumentException("O maior preço deve ser maior que 0");
        }
        this.highestPrice = highestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        if (lowestPrice <= 0) {
            throw new IllegalArgumentException("O menor preço deve ser maior que 0");
        }
        this.lowestPrice = lowestPrice;
    }

    public void setAveragePrice(double averagePrice) {
        if (averagePrice <= 0) {
            throw new IllegalArgumentException("O preço médio deve ser maior que 0");
        }
        this.averagePrice = averagePrice;
    }
}
