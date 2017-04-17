package za.co.mwongho.sample.awaitility.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import za.co.mwongho.sample.awaitility.model.Product;

public interface ProductService {
	@GET("products")
	Call<List<Product>> getProducts();
}
