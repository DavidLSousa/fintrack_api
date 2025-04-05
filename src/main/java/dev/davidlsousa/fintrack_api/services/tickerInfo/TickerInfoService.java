package dev.davidlsousa.fintrack_api.services.tickerInfo;

import dev.davidlsousa.fintrack_api.Model.tickers.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TickerInfoService implements TickerInfoInterface {

    @Autowired
    TickerInfoAdapter adapter;

    @Override
    public Ticker getInfoTicker(String tickerDTO) {
        return adapter.getInfoTicker(tickerDTO);
    }
}
