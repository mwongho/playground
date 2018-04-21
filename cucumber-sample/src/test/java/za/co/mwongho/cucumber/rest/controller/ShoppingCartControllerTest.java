package za.co.mwongho.cucumber.rest.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.mwongho.cucumber.buisness.domain.model.Product;
import za.co.mwongho.cucumber.buisness.repository.ProductRepository;
import za.co.mwongho.cucumber.rest.domain.model.OrderLine;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private ProductRepository productRepository;
    
    static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    
    @Test
	public void getCart() throws Exception {
		mvc.perform(get("/cart")).andExpect(status().isOk());
	}
    
    @Test
	public void addProduct() throws Exception {

		OrderLine orderLine = new OrderLine("apple", 1);

		given(productRepository.findByName("apple")).willReturn(Optional.ofNullable(new Product(1L, "apple")));

		mvc.perform(post("/cart").contentType(MediaType.APPLICATION_JSON).content(toJson(orderLine)))
				.andExpect(status().isOk());
	}
	
}
