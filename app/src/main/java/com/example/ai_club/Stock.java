package com.example.ai_club;

public class Stock {
    private String name;
    private String ticker;
    private double price;
    private double priceChange;

    private int iconResourceId;

    public Stock(String name, String ticker, double price, double priceChange) {
        this.name = name;
        this.ticker = ticker;
        this.price = price;
        this.priceChange = priceChange;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

}

