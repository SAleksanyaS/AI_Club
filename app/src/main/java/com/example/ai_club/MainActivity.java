package com.example.ai_club;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    //private static final String API_KEY = "5DENH3PY2KSN1RIR";
    private static final String API_BASE_URL = "https://www.alphavantage.co/";
    String[] CompanyName = {"Apple", "Amazon.com", "AMD", "Яндекс", "Electronic Arts", "TCS Group", "Alphabet Class A", "HP",  "Microsoft Corporation", "Роснефть", "eBay", "Visa", "Caterpillar"};
    String[] CompanyTiket = {"AAPL", "AMZN", "AMD", "YNDX", "EA", "TCS", "GOOGL", "HPQ", "MSFT", "ROSN", "EBAY", "V", "CAT"};
    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;
    private List<Stock> stockList;
    private Timer timer;
    int k = 0;
    EditText TokenEditText;
    Button createTokenBtn;
    ImageButton update_button, portfel_button, proekt_button, close_button;
    Spinner sort_spinner;
    TextView first;
    private long backPressedTime;
    private  Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        TokenEditText = findViewById(R.id.token_edit_text);
        createTokenBtn = findViewById(R.id.create_token_btn);
        first = findViewById(R.id.firsttext);
        update_button = findViewById(R.id.update_button);
        sort_spinner = findViewById(R.id.sort_spinner);
        portfel_button = findViewById(R.id.portfel_button);
        proekt_button = findViewById(R.id.proekt_button);
        close_button = findViewById(R.id.close_button);


        update_button.setOnClickListener(v-> Utility.showToast(MainActivity.this,"используйте токен 5DENH3PY2KSN1RIR"));
        portfel_button.setOnClickListener(v-> Utility.showToast(MainActivity.this,"Вы уже здесь!"));
        proekt_button.setOnClickListener(v-> Utility.showToast(MainActivity.this,"Данные обновляются раз в 90 секунд!"));

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        createTokenBtn.setOnClickListener(v-> createToken());

    }

    private void fetchStockData(String API_KEY) {
        OkHttpClient client = new OkHttpClient();
        for(int i = 0; i<4; ++i) {
            int tik = k;
            int nam = k;
            String url = API_BASE_URL + "query?function=GLOBAL_QUOTE&symbol=" + CompanyTiket[tik] + "&apikey=" + API_KEY;
            Request request = new Request.Builder()
                    .url(url)
                    .build();


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Показать сообщение об ошибке
                            Toast.makeText(MainActivity.this, "Ошибка при запросе данных", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Gson gson = new GsonBuilder().create();
                        StockDataResponse stockDataResponse = gson.fromJson(json, StockDataResponse.class);
                        StockQuote stockQuote = stockDataResponse.getStockQuote();

                        // Создайте объект Stock и установите полученные данные акции
                        Stock stock = new Stock(
                                CompanyName[nam],
                                stockQuote.getTicker(),
                                stockQuote.getCurrentPrice(),
                                stockQuote.getPriceChange()
                        );

                        stockList.add(stock);

                        // Добавьте акцию в список

                        // Обновите пользовательский интерфейс на основе изменений в данных
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stockAdapter.notifyDataSetChanged();
                            }
                        });
                    }  else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Показать сообщение об ошибке
                                Toast.makeText(MainActivity.this, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
            if(k >= 12) {
                k = 0;
            }
            else{k += 1;}
        }

    }


    void createToken(){
        String token  = TokenEditText.getText().toString();
        boolean isValidated = validateData(token);
        if(!isValidated){
            return;
        }
        TokenEditText.setVisibility(View.INVISIBLE);
        createTokenBtn.setVisibility(View.INVISIBLE);
        first.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stockList = new ArrayList<>();
        stockAdapter = new StockAdapter(this, stockList);
        recyclerView.setAdapter(stockAdapter);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fetchStockData(token);
            }}, 0, 65 * 1000);
    }

    boolean validateData(String token){
        //validate the data that are input by user.
        if(token.length()<16 || token.length()>16){
            TokenEditText.setError("Токен должен содержать 16 символов");
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime + 3000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(), getText(R.string.exitt),Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}