package dev.davidlsousa.fintrack_api.Model.tickers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "tickers")
@Table(name = "tickers")
@Getter
@NoArgsConstructor
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nameTicker;

    @Column(nullable = false, unique = true)
    private String ticker;

    @Column(nullable = false)
    private int numberOfTickers;

    @Column(nullable = false)
    private double totalValuePurchased;

    @Column(nullable = false)
    private double highestPrice;

    @Column(nullable = false)
    private double lowestPrice;

    @Column(nullable = false)
    private double averagePrice;

//    @ElementCollection
//    @CollectionTable(name = "ticker_history", joinColumns = @JoinColumn(name = "ticker_id"))
//    private List<Map<String, Object>> history;

    public Ticker(String nameTicker, String ticker, int numberOfTickers,
                  double totalValuePurchased, double highestPrice,
                  double lowestPrice, double averagePrice) {

        this.nameTicker = nameTicker;
        this.ticker = ticker;
        this.setNumberOfTickers(numberOfTickers);
        this.setTotalValuePurchased(totalValuePurchased);
        this.setHighestPrice(highestPrice);
        this.setLowestPrice(lowestPrice);
        this.setAveragePrice(averagePrice);
    }

    // Validações nos setters
    public void setNumberOfTickers(int numberOfTickers) {
        if (numberOfTickers <= 0) {
            throw new IllegalArgumentException("O número de tickers deve ser maior que 0");
        }
        this.numberOfTickers = numberOfTickers;
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
