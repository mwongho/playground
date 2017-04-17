package test.za.co.mwongho.sample.awaitility;

import static org.awaitility.Awaitility.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.MediaType;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import za.co.mwongho.sample.awaitility.model.Product;
import za.co.mwongho.sample.awaitility.service.ProductService;

public class AwaitilityTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AwaitilityTest.class);
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private MockWebServer server;
	private ProductService service;
	
	@Before
	public void setUp() throws Exception {
		
		this.server = new MockWebServer();
        this.server.start();
        
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(this.server.url("/"))
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
		this.service = retrofit.create(ProductService.class);
	}
	
	@Test
	public void testAsyncCall() throws Exception {
		String fileName = "products.json";
		server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(convertStreamToString(fileName))
                .throttleBody(1, 1, TimeUnit.SECONDS));
		
		Call<List<Product>> call = this.service.getProducts();
		
		Response<List<Product>> response = await().atMost(5, TimeUnit.SECONDS).until(executeCall(call), is(notNullValue()));
		assertThat(response.isSuccessful(), is(true));
		
		List<Product> products = response.body();
		assertThat(products, is(not(IsEmptyCollection.empty())));
		
		logger.debug("products is {}", products.toString());
	}
	
	private Callable<Response<List<Product>>> executeCall(Call<List<Product>> call) {
	      return new Callable<Response<List<Product>>>() {
	            public Response<List<Product>> call() throws Exception {
	                  return call.execute();
	            }
	      };
	}
	
    private static String convertStreamToString(String fileName) throws Exception {
    	StringBuilder sb = new StringBuilder();
    	
    	InputStream is = AwaitilityTest.class.getResourceAsStream("/"+fileName);
    	
    	try(BufferedReader reader = new BufferedReader(new InputStreamReader(is));  ) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
    	}
    	
        return sb.toString();
    }

}
