package test.za.co.mwongho.spring_speedment.db0.spring_speedment.customer;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.company.spring_speedment.Application;
import com.company.spring_speedment.db0.spring_speedment.customer.Customer;

@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class CustomerControllerTest extends AbstractTestNGSpringContextTests {
	
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
    private MockMvc mockMvc;
    
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
    private List<Customer> customerList;
    
    private Customer customer;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    @BeforeClass
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        
        this.customerList = new ArrayList<>();
        this.customerList.add(new CustomerBuilder().id(1).name("cust1").build());
        
        this.customer = new CustomerBuilder().id(3).name("cust3").build();
    }
    
    @Test(dependsOnMethods={"updateCustomer"})
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/customer/"+this.customer.getId()))
                .andExpect(status().isNoContent());
    }
    
    @Test
    public void creatCustomer() throws Exception {
    	String customerJson = json(customer);
    	
        mockMvc.perform(post("/customer").contentType(contentType).content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", equalTo(this.customer.getName())));
    }
    
    @Test(dependsOnMethods={"creatCustomer"})
    public void updateCustomer() throws Exception {
    	String customerJson = json(this.customer);
    	
        mockMvc.perform(put("/customer/"+this.customer.getId()).contentType(contentType).content(customerJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    

    
    @Test
    public void getCustomerNotFound() throws Exception {
        mockMvc.perform(get("/customer/99"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void getCustomerFound() throws Exception {
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
    
    
    @Test
    public void getCustomers() throws Exception {
        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(new Long(this.customerList.get(0).getId()).intValue())))
                .andExpect(jsonPath("$[0].name", equalTo(this.customerList.get(0).getName())));
    }
    
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
    
}
