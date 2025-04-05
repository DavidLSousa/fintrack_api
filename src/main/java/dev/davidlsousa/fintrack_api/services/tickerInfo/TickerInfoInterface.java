package dev.davidlsousa.fintrack_api.services.tickerInfo;

import dev.davidlsousa.fintrack_api.Model.tickers.Ticker;

public interface TickerInfoInterface {

    Ticker getInfoTicker(String tickerDTO);

}
