package com.example.AuthBasic.controller;


import com.example.AuthBasic.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class HelloControllerTest {
    private final HttpHeaders headers = new HttpHeaders();
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;



    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.defaultHeader("Authorization","sd")
                  .rootUri("http://localhost:"+port);
          testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }

    @Test
    void hello() {
        comprobar("adrian","/api/hola","Hello World USER");

    }

    @Test
    void helloWorld() {
        comprobar("adrian2","/api/world","Hello World ADMIN");

    }

    void comprobar(String username,String url,String message) {
        headers.setBasicAuth(username,"1234");
        HttpEntity<User> request = new HttpEntity<>( headers);
        ResponseEntity<String> response = testRestTemplate.exchange(url, HttpMethod.GET, request, String.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(message, response.getBody());

    }


}