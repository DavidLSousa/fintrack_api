package dev.davidlsousa.fintrack_api.Model.tickers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TickerRequestDTO {

    private String ticker;
    private int numberOfTicker;
    private double totalValuePurchased;
}
