package com.example.ai_club;

import com.google.gson.annotations.SerializedName;

public class StockQuote {
    @SerializedName("01. symbol")
    private String ticker;

    @SerializedName("02. open")
    private double openPrice;

    @SerializedName("03. high")
    private double highPrice;

    @SerializedName("04. low")
    private double lowPrice;

    @SerializedName("05. price")
    private double currentPrice;

    @SerializedName("09. change")
    private double priceChange;

    public String getTicker() {
        return ticker;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getPriceChange() {
        return priceChange;
    }
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public StockQuote(String companyName, String ticker, double currentPrice, double priceChange) {
        this.companyName = companyName;
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.priceChange = priceChange;
    }
}
