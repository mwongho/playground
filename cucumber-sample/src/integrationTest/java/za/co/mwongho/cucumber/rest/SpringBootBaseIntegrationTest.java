package za.co.mwongho.cucumber.rest;

import java.util.Collections;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import za.co.mwongho.cucumber.Application;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class SpringBootBaseIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Before
    public void before() {
        // demo to show how to add custom header Globally for the http request in spring test template , like IV user header
    	restTemplate.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders()
                    .add("iv-user", "user");
            return execution.execute(request, body);
        }));
    }

}
