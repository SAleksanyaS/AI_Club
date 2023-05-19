package com.example.ai_club;

import com.google.gson.annotations.SerializedName;

public class StockDataResponse {
    @SerializedName("Global Quote")
    private StockQuote stockQuote;

    public StockQuote getStockQuote() {
        return stockQuote;
    }
}