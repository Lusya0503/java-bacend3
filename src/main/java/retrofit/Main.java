package retrofit;

import java.io.IOException;
import java.util.List;

import lesson4.Product;
import retrofit.api.MiniMarketApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main {
    public static void main(String[] args) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8189/market/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MiniMarketApi api = retrofit.create(MiniMarketApi.class);

        Response<List<Product>> response = api.getProducts().execute();

        List<Product> products = response.body();

        System.out.println(products);

        Response<Product> response1 = api.getProduct(100L).execute();

        System.out.println(response1);

    }
}