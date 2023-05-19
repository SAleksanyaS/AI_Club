package com.example.ai_club;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder> {
    private Context context;
    private List<Stock> stockList;

    public StockAdapter(Context context, List<Stock> stockList) {
        this.context = context;
        this.stockList = stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_item, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        holder.nameTextView.setText(stock.getName());
        holder.tickerTextView.setText(stock.getTicker());
        holder.priceTextView.setText(String.valueOf(stock.getPrice()));
        holder.changeTextView.setText(String.valueOf(stock.getPriceChange()));
        holder.Plus.setText(" ");

        if(stock.getPriceChange() > 0){
            holder.changeTextView.setText(String.valueOf(stock.getPriceChange()));
            holder.changeTextView.setTextColor(Color.GREEN);
            holder.changeTextProcent.setTextColor(Color.GREEN);
            holder.Plus.setTextColor(Color.GREEN);
        }
        if(stock.getPriceChange() < 0){
            holder.changeTextView.setText(String.valueOf(stock.getPriceChange()));
            holder.Plus.setText(" ");
            holder.changeTextView.setTextColor(Color.RED);
            holder.changeTextProcent.setTextColor(Color.RED);
        }
        //if(stock.getPriceChange() == 0){
        //  holder.changeTextView.setText(String.valueOf(stock.getPriceChange()));
        //  holder.Plus.setText(" ");
        //  holder.changeTextView.setTextColor(Color.BLACK);
        //  holder.changeTextProcent.setTextColor(Color.BLACK);
        //  }

        switch (stock.getTicker()){
            case "AAPL":
                holder.iconImageView.setImageResource(R.drawable.appl);
                break;
            case "AMD":
                holder.iconImageView.setImageResource(R.drawable.amd);
                break;
            case "AMZN":
                holder.iconImageView.setImageResource(R.drawable.amzn);
                break;
            case "CAT":
                holder.iconImageView.setImageResource(R.drawable.cat);
                break;
            case "EA":
                holder.iconImageView.setImageResource(R.drawable.ea);
                break;
            case "EBAY":
                holder.iconImageView.setImageResource(R.drawable.ebay);
                break;
            case "GOOGL":
                holder.iconImageView.setImageResource(R.drawable.googl);
                break;
            case "ROSN":
                holder.iconImageView.setImageResource(R.drawable.rosn);
                break;
            case "HPQ":
                holder.iconImageView.setImageResource(R.drawable.hpq);
                break;
            case "MSFT":
                holder.iconImageView.setImageResource(R.drawable.msft);
                break;
            case "TCS":
                holder.iconImageView.setImageResource(R.drawable.tcs);
                break;
            case "V":
                holder.iconImageView.setImageResource(R.drawable.v);
                break;
            case "YNDX":
                holder.iconImageView.setImageResource(R.drawable.yndx);
                break;
            case "SBER":
                holder.iconImageView.setImageResource(R.drawable.sber);
                break;
            case "GAZP":
                holder.iconImageView.setImageResource(R.drawable.gazp);
                break;
        }


        // Здесь вы можете использовать стороннюю библиотеку для загрузки иконок акций и установки их в ImageView
        // Например, используя Picasso или Glide:
        // Picasso.get().load(stock.getIconUrl()).into(holder.iconImageView);
        // Glide.with(context).load(stock.getIconUrl()).into(holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        public ImageView iconImageView;
        public TextView nameTextView;
        public TextView tickerTextView;
        public TextView priceTextView;
        public TextView changeTextView;
        public TextView changeTextProcent;
        public TextView Plus;
        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            tickerTextView = itemView.findViewById(R.id.tickerTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            changeTextView = itemView.findViewById(R.id.changeTextView);
            changeTextProcent = itemView.findViewById(R.id.changeTextViewprocent);
            Plus = itemView.findViewById(R.id.plus);
        }
    }
}
